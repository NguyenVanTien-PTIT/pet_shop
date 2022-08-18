import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements OnInit {
  carouselOptions =
    {
      items: 1,
      dots: true,
      navigation: false,
      loop: true,
      margin: 0,
      autoplay: true,
      animateOut: 'fadeOut',
      autoHeight: true,
      autoHeightClass: 'owl-height',

  };


  images = [

    {
      text: 'Festive Deer',
      image: '../../../../assets/images/slide1.png'
    },
    {
      text: 'Festive Deer',
      image: '../../../../assets/images/slide2.png'
    }
  ];

  constructor() { }

  ngOnInit() {
  }


}
