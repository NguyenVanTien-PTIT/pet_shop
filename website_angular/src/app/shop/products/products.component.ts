import { LoginService } from 'src/app/shop/services/login.service';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { FlatTreeControl } from '@angular/cdk/tree';
import { MatTreeFlattener, MatTreeFlatDataSource } from '@angular/material';
import { Router } from '@angular/router';
import { ProductService } from '../services/product.service';



@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  private idUser: number;

  page:number = 1;
  totalProduct: number;
  pageSize: number= 6;
  priceValue: number = 10000;
  //Chỉ hiển thị phân trang khi cần thiết
  paginationIsDisplay: boolean = true;

  // Pagination = 1: phân trang theo tìm kiếm tất cả sản phẩm
  // Pagination = 2: phân trang theo tìm kiếm sản phẩm theo giá
  paginationCase:number = 1;
  carouselOptions = 
  {
    items: 1, 
    dots: false, 
    navigation: false, 
    loop:true,
    margin:10,
    autoplay:true,
    animateOut: 'fadeOut',
    autoHeight: true,
    autoHeightClass: 'owl-height',
    
}
  products = [];
  category = [];
  
  constructor(
    private router: Router, 
    private productService: ProductService,
    private toastr: ToastrService,
    ) 
  {
  }

  ngOnInit() {
    this.getAllProductsOnPage();
  }

  //Lấy danh sách các sản phẩm ở trang hiện tại khi click vào danh mục
  getAllProductsOnPage(){
    if(this.paginationCase == 2){
      this.page = 1;
    }
    this.paginationCase = 1;
    this.paginationIsDisplay = true;
    //Lấy các sản phẩm thuộc trang hiện tại
    this.productService.getAllProducts(this.page, this.pageSize).subscribe((data) => {
      this.products=data.productDTOList;
      this.category = data.categoryDTOList;
      console.log(this.category);
    });
    //Lấy tổng số lượng sản phẩm tìm kiếm được trên database
    this.productService.totalProduct().subscribe((data) => {
      this.totalProduct = data;
      console.log('totalProduct: '+this.totalProduct);
    })
  }

  // Tìm kiếm sản phẩm theo danh mục
  findProductByCategory(id, event) {
    this.paginationIsDisplay = false;
    this.productService.findProductByCategory(id).subscribe((data) => {
      this.products = data;
    })
  }

  //Tìm kiếm sản phẩm theo giá
  updatePrice(event, page){
    if(this.paginationCase ==1){
      this.page = 1;
    }
    this.paginationCase = 2;
    console.log(event.value);
    if(typeof event.value != 'undefined'){
      this.priceValue = event.value;
    }
    console.log("priceValue= "+ this.priceValue);
    this.productService.findByPrice(this.priceValue, this.page, this.pageSize).subscribe((data) => {
      console.log(data);
      this.products = data.productDTOList;
      this.totalProduct = data.totalProduct;
      //Kiểm tra số lượng để ẩn phân trang
      if(this.totalProduct > 0){
        this.paginationIsDisplay = true;
      }else{
        this.paginationIsDisplay = false;
      }
    });
  }

  //Lấy danh sách sản phẩm trang tiếp theo
  handlePageChange(event:any) {
    this.page = event;
    console.log('page: '+this.page);
    if(this.paginationCase == 1){
      this.productService.getAllProducts(this.page, this.pageSize).subscribe((data) => {
        this.products=data.productDTOList;
      });
    }else if(this.paginationCase ==2){
      this.updatePrice(this.priceValue, this.page);
    }
  }

  //Chuyển hướng url sang page single-product
  productHome(id) {
    this.router.navigate(['product/'+id]);
  }

  //Thêm vào giỏ hàng khi click button add to cart
  addToCart(idProduct:number) {
    //Lấy user từ localStorage và kiểm tra 
    let userCurrent: any = JSON.parse(localStorage.getItem('userCurrent'));
    console.log(userCurrent)

    if(userCurrent ===null){
      this.toastr.warning('Vui lòng đăng nhập để thêm vào giỏ hàng!', 'Thông báo');
      return;
    }
    this.idUser = userCurrent.id;
    console.log('Them vao gio hang: '+ idProduct);
    //Thêm vào giỏ hàng
    this.productService.addProductToCart(this.idUser, idProduct).subscribe((data) => {
      console.log('Nhận dữ liệu khi click add to cart');
      console.log(data);
      this.toastr.success('Thêm vào giỏ hàng thành công');
    });
  }

  //Chỉnh giá trên thanh trượt
  formatLabel(value: number) {
    if (value >= 10000 && value <1000000) {
      return Math.round(value / 1000) + 'k';
    }else if(value >=1000000){
      return Math.round(value / 1000000) + 'M';
    }

    return value;
  }

  getPathImage(image:string) : string{
    return 'http://localhost:8080/uploads/'+ image;
  }
}

