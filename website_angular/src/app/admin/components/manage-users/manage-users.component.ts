import { ActionPopupComponent } from './action-popup/action-popup.component';
import { PopupDeleteComponent } from './popup-delete/popup-delete.component';
import { ToastrService } from 'ngx-toastr';
import { ManageUserService } from './../../services/manage-user.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.scss']
})
export class ManageUsersComponent implements OnInit {
  page = 1;
  pageSize = 5;
  totalUsers: number;
  users: any;
  // Chỉ hiện thị phân trang khi cần thiết
  paginationIsDisplay = true;

  constructor(
    private managerUserService: ManageUserService,
    public toast: ToastrService,
    private dialog: MatDialog,
    private router: Router,
  ) { }

  ngOnInit() {
    this.checkUser();
  }

  checkUser() {
    const currentUser = JSON.parse(localStorage.getItem('userCurrent'));

    if (!currentUser) {
      this.router.navigate(['/home']);
      return;
    }
    let checked = false;
    currentUser.roles.forEach(role => {
      if (role === 'ADMIN') {
        checked = true;
      }
    });
    if (!checked) {
      this.toast.error('Từ chối truy cập');
      this.router.navigate(['/home']);
      return;
    }
    this.loadUsers();
  }

  // Load data
  public loadUsers() {
    this.managerUserService.getAllUsers(this.page, this.pageSize).subscribe(data => {
      if (data === null) {
        this.router.navigate(['/home']);
        this.toast.error('Từ chối truy cập');
        return;
      }
      this.users = data.map(
          user => ({
            ...user,
            rolesName: user.roles.map(this.mappedRole.bind(this))
          })
      );

    });
    this.managerUserService.countUsers().subscribe(count => {
      this.totalUsers = count;
      if (count > 0) {
        this.paginationIsDisplay = true;
      } else {
        this.paginationIsDisplay = false;
      }
    });
  }

  // Lấy danh sách user trang tiếp theo
  handlePageChange(event) {
    this.page = event;
    this.loadUsers();
  }

  // Xóa
  delete(u: any, action: string) {
    u.action = action;
    // Mở popup xóa
    const dialogRef = this.dialog.open(PopupDeleteComponent, {
      data: {
        data: u,
        title: 'Xóa người dùng',
        message: 'Bạn có muốn xóa người dùng này không?'
      },
      disableClose: true,
      hasBackdrop: true,
      width: '420px'
    });

    // Nhận dữ liệu và xử lý xóa sau khi popup xóa đóng lại
    dialogRef.afterClosed().subscribe(result => {
      if (result === undefined) {
        console.log('click ra ngoài');
        return;
      }
      // Xóa và thông báo kết quả
      if (result.event === 'delete') {
        this.managerUserService.deleteUser(result.data.id).subscribe(
            (res) => {
              if (res.httpStatus === 'BAD_REQUEST') {
                this.toast.error(res.msg);
              } else if (res.httpStatus === 'OK') {
                this.toast.success(res.msg);
                this.loadUsers();
              }
            }
        );
      }
    });
  }

  // Sửa user
  edit(u: any, action: string) {
    u.action = action;
    // Mở popup action
    const dialogRef = this.dialog.open(ActionPopupComponent, {
      data: u
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === undefined) {
        return;
      }
      if (result.event === 'edit') {
        this.managerUserService.updateUser(result.data).subscribe(data => {
          if (data.httpStatus === 'OK') {
            this.toast.success(data.msg);
          } else {
            this.toast.error(data.msg);
          }
          this.loadUsers();
        });
      }
    });
  }

  // Thêm user
  add(action: string) {
    const newUser: any = {};
    newUser.action = action;
    // Mở popup action
    const dialogRef = this.dialog.open(ActionPopupComponent, {
      data: newUser
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === undefined) {
        console.log('click ra ngoài');
        return;
      }

      if (result.event === 'add') {
        this.managerUserService.addUser(result.data).subscribe(data => {
          if (data.httpStatus === 'OK') {
            this.toast.success(data.msg);
          } else {
            this.toast.error(data.msg);
          }
          this.loadUsers();
        });
      }
    });
  }

  mappedRole(role: string): string {
    return role === 'ADMIN' ? 'Nhân viên quản lý' : 'Khách hàng';
  }
}
