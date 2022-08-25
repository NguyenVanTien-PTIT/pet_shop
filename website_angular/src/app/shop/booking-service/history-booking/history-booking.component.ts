import { Component, OnInit } from '@angular/core';
import {OderService} from '../../services/oder.service';
import {Router} from '@angular/router';
import {BookingService} from '../../services/booking.service';

@Component({
  selector: 'app-history-booking',
  templateUrl: './history-booking.component.html',
  styleUrls: ['./history-booking.component.scss']
})
export class HistoryBookingComponent implements OnInit {
  currentUser = JSON.parse(localStorage.getItem('userCurrent'));
  bookings = [];

  status = {
    0: 'Đang chờ xác nhận',
    1: 'Đã xác nhận',
    2: 'Đã hoàn thành'
  };

  constructor(
      private bookingService: BookingService,
      private router: Router) {
  }

  ngOnInit() {
    if (!this.currentUser) {
      this.router.navigate(['/home']);
      return;
    }
    this.load();
  }

  // Lấy danh sách đặt lịch
  load() {
    this.bookingService.getAllByCurrentUser(this.currentUser.id).subscribe(data => {
      this.bookings = data;
    });
  }
}
