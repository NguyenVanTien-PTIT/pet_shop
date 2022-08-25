import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {BookingService} from '../../../shop/services/booking.service';

@Component({
  selector: 'app-manage-booking',
  templateUrl: './manage-booking.component.html',
  styleUrls: ['./manage-booking.component.scss']
})
export class ManageBookingComponent implements OnInit {
  currentUser = JSON.parse(localStorage.getItem('userCurrent'));
  statusCurrent = 0;
  status = {
    0: 'Đang chờ xác nhận',
    1: 'Đã xác nhận',
    2: 'Đã hoàn thành'
  };

  bookings = [];
  allBooking = [];

  constructor(
      private bookingService: BookingService,
      private cdr: ChangeDetectorRef,
      private toast: ToastrService) { }

  ngOnInit() {
    this.statusCurrent = 0;
    this.bookingService.getAll().subscribe(res => {
      this.allBooking = res;
      this.getAllBookingByStatus(this.statusCurrent);
    });
  }

  getAllBookingByStatus(status) {
    this.statusCurrent = status;
    this.bookings = this.allBooking.filter(b => b.status === this.statusCurrent);
    this.cdr.detectChanges();
  }

  // Click duyệt
  changeStatus(booking) {
    // Duyệt sang xác nhận
    booking.status = this.statusCurrent + 1;

    this.bookingService.updateStatus(booking).subscribe(() => {
      this.toast.success('Duyệt thành công');
      this.ngOnInit();
    });
  }
}
