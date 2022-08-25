import {Component, Inject, OnInit, Optional} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {FormGroup} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {ServiceWorker} from '../../../admin/services/manage-service-worker.service';
import {WorkerDetailsComponent} from '../worker-details/worker-details.component';

@Component({
  selector: 'app-choose-worker-popup',
  templateUrl: './choose-worker-popup.component.html',
  styleUrls: ['./choose-worker-popup.component.scss']
})
export class ChooseWorkerPopupComponent implements OnInit {

  workers: ServiceWorker[] = [];
  createForm: FormGroup;

  carouselOptions = {
    items: 4,
    dots: false,
    navigation: false,
    nav: true,
    loop: false,
    margin: 20,
    autoplay: false,
  };

  constructor(
      public dialogRef: MatDialogRef<ChooseWorkerPopupComponent>,
      @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
      private matDialog: MatDialog,
  ) {
    this.workers = data;
  }

  ngOnInit() {

  }

  choose(item: ServiceWorker) {
    this.dialogRef.close(item);
  }

  closeDialog() {
    this.dialogRef.close();
  }

  showDetails(item) {
    this.matDialog.open(WorkerDetailsComponent, {
      data: item,
      width: window.innerWidth + 'px',
      maxHeight: 500
    });
  }

  getPathImage(image: string): string {
    return 'http://localhost:8080/uploads/' + image;
  }
}
