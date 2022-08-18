import { BehaviorSubject } from 'rxjs';
import { HttpClientModule, HttpResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { LoginService } from '../services/login.service';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../interfaces/Ilogin';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  checkSignUp = false;
  loginForm: FormGroup;
  user = {} as User;
  public signUpForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    fullname: new FormControl(''),
    phoneNumber: new FormControl(''),
    address: new FormControl(''),
  });

  constructor(
    public dialogRef: MatDialogRef<LoginComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private loginService: LoginService,
    private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService,

  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [''],
      password: ['']
    });
  }

  // Lấy giá trị username và password gửi lên server
  public createData() {
    const newUser: any = {};
    for (const controlName in this.loginForm.controls) {
      if (controlName) {
        newUser[controlName] = this.loginForm.controls[controlName].value;
      }
    }
    return newUser;
  }

  // Đăng nhập
  login() {
    this.loginService.checkLogin(this.createData()).subscribe((data) => {
      this.user = data.userDTO;
      if (this.user != null) {
        const currentUser = JSON.stringify(data.userDTO);

        // Lưu user hiện tại và token vào localStorage
        localStorage.setItem('token', data.token);
        localStorage.setItem('userCurrent', currentUser);
        this.user.username = this.loginForm.value.username;
        this.loginService.ckeckHaslogin$.next(true);
        for (const i of data.userDTO.roles) {
          if (i === 'ADMIN') {
            this.toastr.success(data.msg);
            this.onNoClick();
            // window.location.reload();
            void this.router.navigate(['admin/dashboard']);
            return;
          }
        }
        this.onNoClick();
        this.toastr.success(data.msg);
      } else {
        this.toastr.error('Sai tài khoản hoặc mật khẩu');
      }
    });
  }

  // Lấy giá trị user đăng ký
  public getNewUser() {
    const phoneNumber = this.signUpForm.get('phoneNumber').value;
    this.signUpForm.get('username').setValue(phoneNumber);
    return this.signUpForm.value;
  }

  // Đăng ký
  signUp() {
    this.loginService.addUser(this.getNewUser()).subscribe(data => {
      if (data == null) {
        this.toastr.error('Username đã tồn tại!');
      } else {
        this.signUpForm.reset();
        this.toastr.success('Đăng ký thành công!');
        this.checkSignUp = false;
      }
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  // Chuyển sang đăng ký
  goToSignUp() {
    this.checkSignUp = true;
  }

  // Chuyển về đăng nhập
  goToLogin() {
    this.checkSignUp = false;
  }
}
