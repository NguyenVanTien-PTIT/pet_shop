import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryBookingComponent } from './history-booking.component';

describe('HistoryBookingComponent', () => {
  let component: HistoryBookingComponent;
  let fixture: ComponentFixture<HistoryBookingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoryBookingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
