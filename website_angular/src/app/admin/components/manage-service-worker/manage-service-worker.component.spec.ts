import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageServiceWorkerComponent } from './manage-service-worker.component';

describe('ManageServiceWorkerComponent', () => {
  let component: ManageServiceWorkerComponent;
  let fixture: ComponentFixture<ManageServiceWorkerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageServiceWorkerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageServiceWorkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
