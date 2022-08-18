import { OrderDetailComponent } from './order-detail/order-detail.component';
import { OrderDeletePopupComponent } from './delete-popup/order-delete-popup.component';
import { OrderActionPopupComponent } from './action-popup/order-action-popup.component';
import { MatDialog } from '@angular/material';
import { ManageOrderService } from '../../services/manage-order.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-orders',
  templateUrl: './manage-orders.component.html',
  styleUrls: ['./manage-orders.component.scss']
})
export class ManageOrdersComponent implements OnInit {
  private orders:any;
  statusCurrent:number;
  page:number;
  pageSize:number = 5;
  totalOrders:number;
  status:any = {
    0: 'Đang đặt hàng',
    1: 'Chờ xác nhận',
    2: 'Đang giao hàng',
    3: 'Đã giao hàng'
  };

  constructor(
    private manageOrderService: ManageOrderService, 
    private toast: ToastrService,
    private dialog: MatDialog,
    private router: Router) { }

  ngOnInit() {
    this.page = 1;
    this.statusCurrent = 0;
    this.loadData();
    this.checkUser();
  }

  checkUser() {
    let currentUser = JSON.parse(localStorage.getItem('userCurrent'));

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
  }

  loadData() {
    this.getOrderByStatus(this.statusCurrent);
  }

  getOrderByStatus(status:number){
    if(status !== this.statusCurrent){
      this.page = 1;
    }
    this.statusCurrent = status;
    this.manageOrderService.getOrderByStatus(status, this.page, this.pageSize).subscribe(data => {
      if (null === data) {
        this.toast.error('Từ chối truy cập!');
        this.router.navigate(['/home']);
      }
      this.orders = data;
    });
    this.manageOrderService.countOrderByStatus(status).subscribe(data => {
      this.totalOrders = data;
    });
  }

  handlePageChange(event){
    this.page = event;
    this.getOrderByStatus(this.statusCurrent);
  }

  //Click duyệt
  changeStatus(id:number){
    //Duyệt sang xác nhận
    if(this.statusCurrent === 1){
      this.manageOrderService.changeStatus(id).subscribe(data=>{
        if(data.httpStatus === 'OK'){
          this.toast.success(data.msg);
        }else{
          this.toast.error(data.msg);
        }
        if(this.orders.length == 1 && this.page!== 1){
          this.page -= 1;
        }
        console.log(this.orders)
        this.loadData();
      })
    }
    //Duyệt sang đã giao hàng
    else if(this.statusCurrent === 2){
      this.manageOrderService.changeStatus(id).subscribe(data=>{
        if(data.httpStatus === 'OK'){
          this.toast.success(data.msg);
        }else{
          this.toast.error(data.msg);
        }
        if(this.orders.length == 1 && this.page!== 1){
          this.page -= 1;
        }
        this.loadData();
      })
    }
  }

  //Sửa đơn hàng
  edit(u: any, action: string){
    u.action = action;
    const dialogRef = this.dialog.open(OrderActionPopupComponent, {data: u});

    dialogRef.afterClosed().subscribe(result => {
      if(!result){
        return;
      }
      if(result.event === 'edit'){
        this.manageOrderService.updateOrder(result.data).subscribe(data => {
          if(data.httpStatus === 'OK'){
            this.toast.success(data.msg);
          }else{
            this.toast.error(data.msg);
          }
          this.loadData();
        })
      }
    })
  }

  delete(u, action){
    u.action = action;
    const dialogRef = this.dialog.open(OrderDeletePopupComponent, {data: u});
    dialogRef.afterClosed().subscribe(result => {
      if (!result) {
        return;
      }
      if(result.event === 'delete'){
        this.manageOrderService.deleteOrder(result.data.id).subscribe(data => {
          if(data.httpStatus === 'OK'){
            this.toast.success(data.msg);
          }else{
            this.toast.error(data.msg);
          }
          this.loadData();
        });
      }
    })
  }

  //Mở chi tiết đơn hàng
  openOrderDetail(u:any){
    const dialogRef = this.dialog.open(OrderDetailComponent, {data: u, maxHeight: 500});
  }
}
