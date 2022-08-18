import {ToastrService} from 'ngx-toastr';
import {Component, OnInit} from '@angular/core';
import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';
import {Router} from '@angular/router';
import {ProductService} from '../services/product.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  carouselOptions = {
    items: 1,
    dots: false,
    navigation: false,
    loop: true,
    margin: 10,
    autoplay: true,
    animateOut: 'fadeOut',
    autoHeight: true,
    autoHeightClass: 'owl-height',
  };

  private idUser;
  private listNewProduct:any[] = [];
  private listBestSeller:any[] = [];

  constructor(
    iconRegistry: MatIconRegistry, 
    sanitizer: DomSanitizer,
    private router: Router, 
    private productService: ProductService,
    private toastr: ToastrService) {
      this.loadData();
    // iconRegistry.addSvgIcon(
    //     'thumbs-up',
    //     sanitizer.bypassSecurityTrustResourceUrl('assets/img/examples/thumbup-icon.svg'));
  }

  ngOnInit() {
  }

  loadData() {
    this.productService.getHomePage().subscribe(data => {
      console.log(data);
      this.listNewProduct = data.listNewProduct;
      this.listBestSeller = data.listBestSeller;
    })
  }

  //Chuyển hướng sang trang chi tiết sp
  singleProduct(id: number) {
    this.router.navigate(['product/'+ id]);
  }

  //Thêm vào giỏ hàng khi click button add to cart
  addToCart(idProduct:number) {
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
      this.toastr.success('Thêm vào giỏ hàng thành công', 'Thông báo');
    })
  }

  viewAll(){
    console.log('hahah')
    this.router.navigate(['products'])
  }

  getPathImage(image:string) : string{
    return 'http://localhost:8080/uploads/'+ image;
  }

}
