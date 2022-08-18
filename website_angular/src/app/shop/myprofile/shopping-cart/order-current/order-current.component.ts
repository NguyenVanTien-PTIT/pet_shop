import {FormControl, FormGroup} from '@angular/forms';
import {OderService} from '../../../services/oder.service';
import {Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Component({
    selector: 'app-order-current',
    templateUrl: './order-current.component.html',
    styleUrls: ['./order-current.component.scss'],
})
export class OrderCurrentComponent implements OnInit {
    private id = 0;

    private orders;
    private calculateOrder;
    private shipFee = 0;
    status: number;
    orderId: number;

    public notifyForm = new FormGroup({
        nameUser: new FormControl(''),
        phoneNumber: new FormControl(''),
        address: new FormControl(''),
        orderDate: new FormControl('')
    });

    constructor(
        private orderService: OderService,
        private router: Router,
        private toastr: ToastrService) {
    }

    ngOnInit() {
        this.loadPage();
    }

    // Lấy danh sách sản phẩm và số lượng + order hiện tại của user
    public loadPage() {
        const currentUser = JSON.parse(localStorage.getItem('userCurrent'));

        if (!currentUser) {
            this.router.navigate(['/home']);
            this.toastr.warning('Vui lòng đăng nhập!');
            return;
        }

        this.id = currentUser.id;
        // Load data
        this.orderService.getOder().subscribe((data) => {

            if (!data) {
                this.router.navigate(['/home']);
                this.toastr.warning('Vui lòng đăng nhập!');
                return;
            }

            console.log('order hiện tại của bạn có id user:  ' + this.id);
            console.log(data);
            if (data.ordersDTO) {
                this.status = data.ordersDTO.status;
                this.orderId = data.ordersDTO.id;
                this.calculateOrder = data.ordersDTO;
            }
            if (data.orderProducts) {
                this.orders = data.orderProducts;
            }
            // Set giá trị cho form control nếu có đơn đặt hàng
            if (data.ordersDTO) {
                for (const controlName in this.notifyForm.controls) {
                    if (controlName) {
                        this.notifyForm.controls[controlName].setValue(data.ordersDTO[controlName]);
                    }
                }
            }
            console.log('status order: ', this.status);
            console.log('id order: ', this.orderId);
        });
    }

    // Thanh toán tiền đặt hàng
    payment() {
        console.log('Thanh toán: ');
        this.orderService.payment(this.id, this.orderCurrent()).subscribe((data) => {
            console.log(data);
            this.toastr.info(data.msg, 'Thông báo');
            this.router.navigate(['/home']);
        });
    }

    // Đóng gói order hiện tại thành object gửi lên server khi thanh toán
    orderCurrent(): any {
        const newOrder: any = {};
        newOrder.id = this.calculateOrder.id;
        for (const controlName in this.notifyForm.controls) {
            if (controlName) {
                newOrder[controlName] = this.notifyForm.controls[controlName].value;
            }
        }
        const totalprice = 'totalprice';
        this.status = 1;
        newOrder.status = this.status;
        newOrder[totalprice] = this.calculateOrder.totalprice + this.shipFee;
        return newOrder;
    }

    // Xóa sản phẩm trong order
    removeProductOrder(id: number) {

        this.orderService.removeProductOrder(id).subscribe((data) => {
            console.log('Xóa order product thành công');
            this.orders = data.orderProducts;
            this.calculateOrder = data.ordersDTO;
            this.toastr.success('Đã xóa sản phẩm này!', 'Thông báo');
        });
    }

    // Click vao sản phẩm trong giỏ hàng
    goToProductDetails(id: number) {
       void this.router.navigate(['product/' + id]);
    }

    getPathImage(image: string): string {
        return 'http://localhost:8080/uploads/' + image;
    }
}
