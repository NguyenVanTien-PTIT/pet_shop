import {Component, Inject, OnInit, Optional} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {ToastrService} from 'ngx-toastr';
import {ManageServiceService} from '../../../services/manage-service.service';

@Component({
  selector: 'app-action-popup',
  templateUrl: './action-popup.component.html',
  styleUrls: ['./action-popup.component.scss']
})
export class ActionPopupServiceComponent implements OnInit {


  action: 'add' | 'edit' = 'add';
  service;
  createForm: FormGroup;

  constructor(
      public dialogRef: MatDialogRef<ActionPopupServiceComponent>,
      @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
      private fb: FormBuilder,
      private manageServiceService: ManageServiceService,
      private toast: ToastrService,
  ) {
    this.action = data.action;
    this.service = data;
  }

  ngOnInit() {
    this.loadData();
  }

  // Đổ dữ liệu vào form sửa
  loadData(): void {
    this.createForm = this.fb.group({
      name: ['', Validators.required],
      price: ['', [Validators.required]],
      description: [''],
    });

    // Set form control
    if (this.action === 'edit') {
      this.createForm.patchValue({
        name: this.service.name,
        price: this.service.price,
        description: this.service.description
      });
    }
  }

  save(action: 'add' | 'edit') {
    // Lấy giá trị từ các FormControl
    for (const controlName in this.createForm.controls) {
      this.service[controlName] = this.createForm.controls[controlName].value;
    }

    console.log('worker sau khi sua/ them: ', this.service);

    this.manageServiceService.save(this.service).subscribe(
        (res) => {
          this.toast.success('Thành công');
          this.dialogRef.close({event: 'cancel'});
        }
    );
  }

  closeDialog() {
    this.dialogRef.close({event: 'cancel'});
  }
}
