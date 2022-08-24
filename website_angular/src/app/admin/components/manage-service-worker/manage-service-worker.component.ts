import {Component, OnInit} from '@angular/core';
import {ManageProductService} from '../../services/manage-product.service';
import {MatDialog} from '@angular/material';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {ActionProductPopupComponent} from '../manage-products/action-popup/action-popup.component';
import {DeleteProductPopupComponent} from '../manage-products/delete-popup/delete-popup.component';
import {ManageServiceWorkerService, ServiceWorker} from '../../services/manage-service-worker.service';
import {ActionPopupWorkerComponent} from './action-popup/action-popup.component';

@Component({
    selector: 'app-manage-service-worker',
    templateUrl: './manage-service-worker.component.html',
    styleUrls: ['./manage-service-worker.component.scss']
})
export class ManageServiceWorkerComponent implements OnInit {
    private page = 1;
    private pageSize = 5;

    workers: ServiceWorker[] = [];
    // Chỉ hiện thị phân trang khi cần thiết
    paginationIsDisplay = true;

    constructor(
        private manageServiceWorkerService: ManageServiceWorkerService,
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
        this.manageServiceWorkerService.getAll().subscribe(data => {
            if (data) {
                this.workers = data;
            }
        });
    }

    // Them sản phẩm
    add() {
        this.dialog.open(ActionPopupWorkerComponent, {
            data: {action: 'add'},
            maxHeight: 500
        })
            .afterClosed()
            .subscribe(() => this.getAll());
    }

    // Sửa sản phẩm
    edit(worker: ServiceWorker, action: string) {
        this.dialog.open(ActionPopupWorkerComponent, {
            data: {action: 'edit', ...worker},
            maxHeight: 500
        })
            .afterClosed()
            .subscribe(() => this.getAll());
    }

    delete(worker: any, action: string) {
        worker.action = action;
        const dialogRef = this.dialog.open(DeleteProductPopupComponent, {data: worker});
        dialogRef.afterClosed().subscribe((res) => {
            if (res === undefined) {
                return;
            }
            if (res.event === 'delete') {
                this.manageServiceWorkerService.delete(res.data.id).subscribe(data => {
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
