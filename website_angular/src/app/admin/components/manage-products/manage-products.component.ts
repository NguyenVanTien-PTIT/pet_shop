import { Router } from '@angular/router';
import { CategoryActionPopupComponent } from './category-action-popup/category-action-popup.component';
import { ToastrService } from 'ngx-toastr';
import { ActionProductPopupComponent } from './action-popup/action-popup.component';
import { MatDialog } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { ManageProductService } from '../../services/manage-product.service';
import { DeleteProductPopupComponent } from './delete-popup/delete-popup.component';
import { CategoryDeletePopupComponent } from './category-delete-popup/category-delete-popup.component';

@Component({
  selector: 'app-manage-products',
  templateUrl: './manage-products.component.html',
  styleUrls: ['./manage-products.component.scss']
})
export class ManageProductsComponent implements OnInit {
  private page: number;
  private pageCategory:number;
  private pageSize:number = 5;
  totalProducts : number;
  totalCategory:number;
  products: any;
  categories:any;
  currentCategory:number;
  //Chỉ hiện thị phân trang khi cần thiết
  paginationIsDisplay: boolean = true;
  paginationIsDisplayCate:boolean = true;
  constructor(
    private productService: ManageProductService,
    private dialog: MatDialog,
    private toastr: ToastrService,
    private route: Router
  ) { }

  ngOnInit() {
    this.page = 1;
    this.pageCategory = 1;
    this.checkUser();
  }
  
  checkUser(){
    let currentUser = JSON.parse(localStorage.getItem('userCurrent'));

    if(!currentUser){
      this.route.navigate(['/home']);
      return;
    }
    let checked = false;
    currentUser.roles.forEach(role =>{
      if(role === 'ADMIN'){
        checked = true;
      }
    })
    if(!checked){
      this.toastr.error('Từ chối truy cập');
      this.route.navigate(['/home']);
      return;
    }
    this.loadData();
  }

  loadData() {
    //Lấy ds danh mục
    this.productService.getAllCategory(this.pageCategory, this.pageSize).subscribe((data) =>{
      console.log('hehehe',data)
      if(data == null){
        this.route.navigate(['/home']);
        this.toastr.error('Từ chối truy cập');
        return;
      }
      this.categories = data;
      if(this.categories.length == 0 && this.pageCategory >0){
        this.pageCategory -= 1;
        this.loadData();
        return;
      }
      console.log(data);
      this.currentCategory = data[0].id;
      //Lấy ds sản phẩm và số lượng sản phẩm
      this.getListProduct(this.currentCategory);
    })

    //Lấy tổng slg danh mục
    this.productService.getCountCategory().subscribe((data) =>{
      this.totalCategory = data;
      if(this.totalCategory <= this.pageSize){
        this.paginationIsDisplayCate = false;
      }else{
        this.paginationIsDisplayCate = true;
      }
    })
  }

  getListProduct(idCategory:number){
    this.page = 1;
    this.currentCategory = idCategory;
    this.productService.getProductsByCategory(idCategory, this.page, this.pageSize).subscribe(data =>{
      if(data){
        this.products = data.list;
        this.totalProducts = data.total;
      }
    })
  }

  //Phân trang category
  handlePageCategoryChange(event){
    this.pageCategory = event;
    this.loadData();
  }

  //Phân trang product
  handlePageChange(event){
    this.page = event;
    this.productService.getProductsByCategory(this.currentCategory, this.page, this.pageSize).subscribe(data =>{
      if(data){
        this.products = data.list;
        this.totalProducts = data.total;
      }
    })
  }

  //Them sản phẩm
  add(action: string){
    const product: any = {};
    product.action = action;
    product.categories = this.categories;
    const dialogRef = this.dialog.open(ActionProductPopupComponent, {
      data: product,
      width: window.innerWidth+'px',
      maxHeight: 500 + 'px',
    });
    dialogRef.afterClosed().subscribe(result =>{
      if(!result){
        return;
      }
      if(result.event === 'add'){
        this.productService.addProduct(result.data).subscribe(data =>{
          if(data.httpStatus === 'OK'){
            this.toastr.success(data.msg);
          }else{
            this.toastr.error(data.msg);
          }
          this.getListProduct(this.currentCategory);
        }, error => {
          this.toastr.error("Lỗi!");
        })
      }
    })
  }

  //Sửa sản phẩm
  edit(u:any, action:string){
    u.action = action;
    u.categories = this.categories;
    const dialogRef = this.dialog.open(ActionProductPopupComponent, {
      data: u, 
      width: window.innerWidth+'px',
      maxHeight: 500 + 'px',
    });
    dialogRef.afterClosed().subscribe((result) =>{
      if(result === undefined){
        return;
      }
      if(result.event === 'edit'){
        this.productService.updateProduct(result.data).subscribe(res => {
          if(res.httpStatus === 'OK'){
            this.toastr.success(res.msg);
          }else{
            this.toastr.error(res.msg);
          }
          this.getListProduct(this.currentCategory);
        }, error => {
          this.toastr.error("Lỗi!");
        })
      }
    })
  }

  //XÓa sản phẩm
  delete(u:any, action: string){
    u.action = action;
    const dialogRef = this.dialog.open(DeleteProductPopupComponent, {data: u});
    dialogRef.afterClosed().subscribe((res)=>{
      if(res === undefined){
        return;
      }
      if(res.event === 'delete'){
        this.productService.deleteProduct(res.data.id).subscribe(data =>{
          if(data.httpStatus === 'OK'){
            this.toastr.success(data.msg);
          }else{
            this.toastr.error(data.msg);
          }
          this.getListProduct(this.currentCategory);
        })
      }
    })
  }

  //Sửa danh mục
  editCategory(u:any, action:string){
    u.action = action;
    const dialogRef = this.dialog.open(CategoryActionPopupComponent, {data: u});
    dialogRef.afterClosed().subscribe((result) =>{
      if(result === undefined){
        return;
      }
      if(result.event === 'edit'){
        console.log(result)
        this.productService.updateCategory(result.data).subscribe(res => {
          if(res.httpStatus === 'OK'){
            this.toastr.success(res.msg);
          }else{
            this.toastr.error(res.msg);
          }
          this.loadData();
        })
      }
    })
  }

  //Thêm danh mục
  addCategory(action:string){
    const category:any = {};
    category.action = action;
    const dialogRef = this.dialog.open(CategoryActionPopupComponent, {data: category});
    dialogRef.afterClosed().subscribe(result => {
      if(result === undefined){
        return;
      }
      if(result.event === 'add'){
        this.productService.addCategory(result.data).subscribe(res => {
          if(res.httpStatus === 'OK'){
            this.toastr.success(res.msg);
            this.loadData();
          }else {
            this.toastr.error(res.msg);
          }
        })
      }
    })
  }

  //Xóa danh mục
  deleteCategory(category:any, action:string) {
    category.action = action;
    const dialogRef = this.dialog.open(CategoryDeletePopupComponent, {data: category});
    dialogRef.afterClosed().subscribe(result =>{
      if(result === undefined){
        return;
      }
      if(result.event === 'delete'){
        this.productService.deleteCategory(result.data.id).subscribe(res => {
          if(res.httpStatus === 'OK'){
            this.toastr.success(res.msg);
          }else {
            this.toastr.error(res.msg);
          }
          this.loadData();
        })
      }
    })
  }

  //lay đường dẫn ảnh
  getPathImage(image:string) : string{
    return 'http://localhost:8080/uploads/' + image;
  }
}
