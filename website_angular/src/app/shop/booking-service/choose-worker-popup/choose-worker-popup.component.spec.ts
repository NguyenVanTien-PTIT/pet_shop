import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseWorkerPopupComponent } from './choose-worker-popup.component';

describe('ChooseWorkerPopupComponent', () => {
  let component: ChooseWorkerPopupComponent;
  let fixture: ComponentFixture<ChooseWorkerPopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseWorkerPopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseWorkerPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
