import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {BookingService} from '../services/booking.service';
import {ManageServiceService} from '../../admin/services/manage-service.service';
import {MatDialog} from '@angular/material';
import {ChooseWorkerPopupComponent} from './choose-worker-popup/choose-worker-popup.component';
import {ManageServiceWorkerService} from '../../admin/services/manage-service-worker.service';
import {forkJoin} from 'rxjs';
import {map, tap} from 'rxjs/operators';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-booking-service',
  templateUrl: './booking-service.component.html',
  styleUrls: ['./booking-service.component.scss']
})
export class BookingServiceComponent implements OnInit {
  imgs = [
    'https://daihocthuyhanoi.edu.vn/assets/uploads/news/images/cho-meo-1-1040-1.jpg',
    'https://daihocthuyhanoi.edu.vn/assets/uploads/news/images/cho-meo-1-1040-1.jpg',
    'https://static.hieuluat.vn/uploaded/Images/Original/2021/11/03/cho-meo-co-phai-gia-suc_0311094711.jpg',
    'https://nld.mediacdn.vn/zoom/700_438/2018/2/8/dog-cat-15180764673761723245972.jpg',
    'https://dreampet.com.vn/wp-content/uploads/2021/03/benh-truyen-nhiem-o-cho-meo-2.jpg',
    'https://image.vtc.vn/files/minhhai/2016/09/07/cho-meo-1042.jpg',
    'https://cms.luatvietnam.vn/uploaded/Images/Original/2018/12/19/nuoi-cho-meo_1912222544.jpg',
    'https://tuvanluat.vn/maytech_data/uploads/2018/08/Nu%C3%B4i-ch%C3%B3-m%C3%A8o-e1557224154937-800x500_c.jpg',
    'https://tuvanluat.vn/maytech_data/uploads/2018/08/Nu%C3%B4i-ch%C3%B3-m%C3%A8o-e1557224154937-800x500_c.jpg',
    'https://azpet.com.vn/wp-content/uploads/2020/09/shutterstock_1779958550_huge.jpg'
  ];
  image = '';

  idService = 0;
  service;
  booking;

  workers;

  currentUser = JSON.parse(localStorage.getItem('userCurrent'));

  public bookingForm = this.fb.group({
    clientId: [''],
    serviceId: [''],
    serviceCode: [''],
    serviceName: '',
    appointmentDateStr: [undefined, Validators.required],
    fullname: [undefined, [Validators.required]],
    phone: [undefined, [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
    petName: [undefined, [Validators.required]],
    petType: [undefined, [Validators.required]],
    serviceWorkerId: [undefined, Validators.required]
  });

  constructor(
      private fb: FormBuilder,
      private bookingService: BookingService,
      private manageService: ManageServiceService,
      private router: Router,
      private route: ActivatedRoute,
      private matDialog: MatDialog,
      private serviceWorkerService: ManageServiceWorkerService,
      private datePipe: DatePipe,
      private toastr: ToastrService) {
  }

  ngOnInit() {
    this.idService = +this.route.snapshot.paramMap.get('id')!;
    this.loadPage();
  }

  public loadPage() {
    forkJoin([
      this.serviceWorkerService.getAll(),
      this.manageService.getAll()
    ]).pipe(
        map(([workers, services]) => ({workers, services})),
        tap((data) => {
          this.image = this.randomImg();
          this.workers = data.workers;
          this.service = data.services.find(s => s.id === this.idService);

          this.bookingForm.patchValue({
            clientId: this.currentUser.id,
            serviceId: this.service.id,
            serviceCode: 'MDV' + this.service.id,
            serviceName: this.service.name,
            fullname: this.currentUser.fullname,
            phone: this.currentUser.username
          });
        })
    ).subscribe();
  }

  openWorkerPopup() {

    this.matDialog.open(ChooseWorkerPopupComponent, {
      data: this.workers,
      width: window.innerWidth + 'px',
      maxHeight: 500
    }).afterClosed()
        .subscribe((event) => {
          if (event) {
            this.bookingForm.get('serviceWorkerId').setValue(event.id);
          }
        });
  }

  // Thanh toán tiền đặt hàng
  payment() {
    this.bookingForm.markAllAsTouched();
    if (this.bookingForm.invalid) {
      return;
    }

    const data = this.bookingForm.value;
    data.appointmentDate = this.datePipe.transform(new Date(data.appointmentDateStr), 'yyyy-MM-dd\'T\'HH:MM:ss.SS\'Z\'');

    this.bookingService.save(this.bookingForm.value).subscribe(response => {
      this.toastr.success('Đặt lịch hẹn thành công, lịch hẹn đang chờ được xác nhận');
      void this.router.navigate(['thanks']);
    });
  }

  getPathImage(image: string): string {
    return 'http://localhost:8080/uploads/' + image;
  }

  details(id: number) {
    this.router.navigate(['service/' + id]);
  }

  randomImg(): string {
    return this.imgs[Math.floor(Math.random() * this.imgs.length)];
  }
}
