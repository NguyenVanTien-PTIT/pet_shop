import {Component, Inject, OnInit, Optional} from '@angular/core';
import {ServiceWorker} from '../../../admin/services/manage-service-worker.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-worker-details',
  templateUrl: './worker-details.component.html',
  styleUrls: ['./worker-details.component.scss']
})
export class WorkerDetailsComponent implements OnInit {

  worker: ServiceWorker;

  constructor(public dialogRef: MatDialogRef<WorkerDetailsComponent>,
              @Optional() @Inject(MAT_DIALOG_DATA) public data: any,) {
    this.worker = data;
  }

  ngOnInit() {
  }

  getPathImage(image: string): string {
    return 'http://localhost:8080/uploads/' + image;
  }
}
