import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {DeleteProductPopupComponent} from '../manage-products/delete-popup/delete-popup.component';
import {ManageServiceService} from '../../services/manage-service.service';
import {ActionPopupServiceComponent} from './action-popup/action-popup.component';

@Component({
  selector: 'app-manage-service',
  templateUrl: './manage-service.component.html',
  styleUrls: ['./manage-service.component.scss']
})
export class ManageServiceComponent implements OnInit {
  private page = 1;
  private pageSize = 5;

  services = [];
  // Chỉ hiện thị phân trang khi cần thiết
  paginationIsDisplay = true;

  constructor(
      private manageServiceService: ManageServiceService,
      private dialog: MatDialog,
      private toastr: ToastrService,
      private route: Router
  ) {
  }

  ngOnInit() {
    this.checkUser();
  }

  checkUser() {
    const currentUser = JSON.parse(localStorage.getItem('userCurrent'));

    if (!currentUser) {
      this.route.navigate(['/home']);
      return;
    }
    let checked = false;
    currentUser.roles.forEach(role => {
      if (role === 'ADMIN') {
        checked = true;
      }
    });
    if (!checked) {
      this.toastr.error('Từ chối truy cập');
      this.route.navigate(['/home']);
      return;
    }
    this.getAll();
  }

  getAll() {
    this.manageServiceService.getAll().subscribe(data => {
      if (data) {
        this.services = data;
      }
    });
  }

  add() {
    this.dialog.open(ActionPopupServiceComponent, {
      data: {action: 'add'},
      maxHeight: 500
    })
        .afterClosed()
        .subscribe(() => this.getAll());
  }

  edit(service, action: string) {
    this.dialog.open(ActionPopupServiceComponent, {
      data: {action: 'edit', ...service},
      maxHeight: 500
    })
        .afterClosed()
        .subscribe(() => this.getAll());
  }

  delete(service: any, action: string) {
    service.action = action;
    const dialogRef = this.dialog.open(DeleteProductPopupComponent, {data: service});
    dialogRef.afterClosed().subscribe((res) => {
      if (res === undefined) {
        return;
      }
      if (res.event === 'delete') {
        this.manageServiceService.delete(res.data.id).subscribe(data => {
          if (data.httpStatus === 'OK') {
            this.toastr.success(data.msg);
          } else {
            this.toastr.error(data.msg);
          }
          this.getAll();
        });
      }
    });
  }


  // lay đường dẫn ảnh
  getPathImage(image: string): string {
    return 'http://localhost:8080/uploads/' + image;
  }
}
