import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-thanks-booking',
  templateUrl: './thanks-booking.component.html',
  styleUrls: ['./thanks-booking.component.scss']
})
export class ThanksBookingComponent implements OnInit {

  constructor(private readonly router: Router) { }

  ngOnInit() {
  }

  back() {
    this.router.navigate(['home']);
  }
}
