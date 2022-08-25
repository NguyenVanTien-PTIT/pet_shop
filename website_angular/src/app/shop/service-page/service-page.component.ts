import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {ManageServiceService} from '../../admin/services/manage-service.service';

@Component({
  selector: 'app-service-page',
  templateUrl: './service-page.component.html',
  styleUrls: ['./service-page.component.scss']
})
export class ServicePageComponent implements OnInit {
  carouselOptions = {
    items: 1,
    dots: false,
    navigation: false,
    loop: true,
    margin: 10,
    autoplay: true,
    animateOut: 'fadeOut',
    autoHeight: true,
    autoHeightClass: 'owl-height',

  };
  services = [];

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

  constructor(
      private router: Router,
      private manageService: ManageServiceService,
      private toastr: ToastrService,
  )
  {}

  ngOnInit() {
    this.getAll();
  }

  // Lấy danh sách dich vu
  getAll() {
    this.manageService.getAll().subscribe((data) => {
      this.services = data.map(item => ({...item, image: this.imgs[Math.floor(Math.random() * 10)]}));
    });
  }
  // //Chuyển hướng url sang page chi tiet
  openDetails(id) {
    this.router.navigate(['service/' + id]);
  }

  getPathImage(image: string): string {
    return 'http://localhost:8080/uploads/' + image;
  }
}
