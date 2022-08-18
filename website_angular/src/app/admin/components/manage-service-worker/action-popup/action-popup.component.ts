import {Component, Inject, OnInit, Optional} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {ManageProductService} from '../../../services/manage-product.service';
import {ToastrService} from 'ngx-toastr';

@Component({
    selector: 'app-action-popup-worker',
    templateUrl: './action-popup.component.html',
    styleUrls: ['./action-popup.component.scss']
})
export class ActionPopupWorkerComponent implements OnInit {

    action: string;
    product: any;
    categories: any;
    public productForm: FormGroup;
    description: any;
    image: File = null;

    constructor(
        public dialogRef: MatDialogRef<ActionPopupWorkerComponent>,
        @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
        private fb: FormBuilder,
        private manageProductService: ManageProductService,
        private toast: ToastrService,
    ) {
        this.action = data.action;
        this.product = data;
        this.categories = data.categories;
    }

    ngOnInit() {
        this.loadData();
    }

    // Đổ dữ liệu vào form sửa
    loadData(): void {
        this.productForm = this.fb.group({
            categoryId: ['', Validators.required],
            name: ['', Validators.required],
            price: ['', Validators.required],
            description: ['', Validators.required],
            image: ['', Validators.required],
            createDate: ['']
        });

        // Set form control
        if (this.action === 'edit') {
            for (const controlName in this.productForm.controls) {
                this.productForm.controls[controlName].setValue(this.product[controlName]);
            }
        }
        this.description = this.product.description;
    }

    changeCategory(event) {
        this.productForm.controls.categoryId.setValue(event.value);
    }

    update() {
        // Lấy giá trị từ các FormControl
        for (const controlName in this.productForm.controls) {
            this.product[controlName] = this.productForm.controls[controlName].value;
        }
        console.log('product sau khi sua/ them: ', this.product);
        this.dialogRef.close({event: this.action, data: this.product});
    }

    closeDialog() {
        this.dialogRef.close({event: 'cancel'});
    }

    onChangeFile(event) {
        this.image = event.target.files[0] as File;
    }

    uploadImage() {
        this.manageProductService.uploadImage(this.image).subscribe(data => {
            if (!data) {
                this.toast.error('Lỗi upload ảnh');
                this.image = null;
            } else if (data.httpStatus === 'BAD_REQUEST') {
                this.image = null;
                this.toast.error(data.msg);
            } else if (data.httpStatus === 'OK') {
                this.productForm.controls.image.setValue(data.data);
                this.toast.success(data.msg);
            }
            console.log(data);
        });
    }

    showRemind() {
        this.toast.error('Vui lòng chọn hình ảnh trước!');
    }
}
