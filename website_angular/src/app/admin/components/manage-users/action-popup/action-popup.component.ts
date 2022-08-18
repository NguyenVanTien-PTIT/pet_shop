import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, Optional, Inject } from '@angular/core';
import {DEFAULT_PASSWORD} from '../../../../shop/interfaces/constant';

@Component({
  selector: 'app-action-popup',
  templateUrl: './action-popup.component.html',
  styleUrls: ['./action-popup.component.scss']
})
export class ActionPopupComponent implements OnInit {

  public profileForm: FormGroup;

  private roles: any[] = [
    {
      checkbox: false,
      name: 'ADMIN',
      nameDisplay: 'Nhân viên quản lý'
    },
    {
      checkbox: false,
      name: 'USER',
      nameDisplay: 'Khách hàng'
    }
  ];
  private roleUpdate: string[] = [];
  private action: string ;
  private user: any = null;

  constructor(
    public dialogRef: MatDialogRef<ActionPopupComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder
  ) {
    if (data.action === 'edit') {
      this.roleUpdate = data.roles;
    }
    this.action = data.action;
    this.user = data;
  }

  ngOnInit() {
    this.profileForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
      password: ['', Validators.required],
      fullname: ['', Validators.required],
      address: ['', Validators.required],
    });
    this.loadData();
  }

  // Đổ dữ liệu vào form sửa
  loadData() {
    // Set form control
    if (this.action === 'edit') {
      this.profileForm.setValue({
        username: this.user.username,
        password: this.user.password,
        fullname: this.user.fullname,
        address: this.user.address
      });
    }
    // Set options list
    if (this.roleUpdate) {
      this.roles.forEach(item => {
        this.roleUpdate.forEach(role => {
          if (item.name === role) {
            item.checkbox = true;
          }
        });
      });
    }
  }

  // Chọn roles
  onCheckboxClick(event, list) {
    // Xóa hết role trong mảng
    this.roleUpdate = [];
    // Gán role theo list checked
    list.selectedOptions.selected.forEach(item => {
      this.roleUpdate.push(item.value);
    });
  }

  // Click vào lưu
  update() {
    // Gán roles vào user
    this.user.roles = this.roleUpdate;
    // Lấy giá trị từ các FormControl
    for (const controlName in this.profileForm.controls) {
      this.user[controlName] = this.profileForm.controls[controlName].value;
    }

    console.log('user sau khi sua/ them: ', this.user);
    this.dialogRef.close({event: this.action, data: this.user});
  }

  // Click vào bỏ qua
  closeDialog() {
    this.dialogRef.close({event: 'cancel'});
  }
}
