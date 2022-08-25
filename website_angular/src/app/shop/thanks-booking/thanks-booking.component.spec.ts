import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThanksBookingComponent } from './thanks-booking.component';

describe('ThanksBookingComponent', () => {
  let component: ThanksBookingComponent;
  let fixture: ComponentFixture<ThanksBookingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThanksBookingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThanksBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
