import {Component, Inject, OnInit, Optional} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {ManageProductService} from '../../../services/manage-product.service';
import {ToastrService} from 'ngx-toastr';
import {ManageServiceWorkerService, ServiceWorker} from '../../../services/manage-service-worker.service';

@Component({
    selector: 'app-action-popup-worker',
    templateUrl: './action-popup.component.html',
    styleUrls: ['./action-popup.component.scss']
})
export class ActionPopupWorkerComponent implements OnInit {

    action: 'add' | 'edit' = 'add';
    worker: ServiceWorker;
    createForm: FormGroup;
    image: File = null;

    constructor(
        public dialogRef: MatDialogRef<ActionPopupWorkerComponent>,
        @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
        private fb: FormBuilder,
        private manageServiceWorkerService: ManageServiceWorkerService,
        private toast: ToastrService,
    ) {
        this.action = data.action;
        this.worker = data;
    }

    ngOnInit() {
        this.loadData();
    }

    // Đổ dữ liệu vào form sửa
    loadData(): void {
        this.createForm = this.fb.group({
            fullname: ['', Validators.required],
            phoneNumber: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
            address: [''],
            description: [''],
        });

        // Set form control
        if (this.action === 'edit') {
            this.createForm.patchValue({
                fullname: this.worker.fullname,
                phoneNumber: this.worker.phoneNumber,
                address: this.worker.address,
                description: this.worker.description
            });
        }
    }

    save(action: 'add' | 'edit') {
        // Lấy giá trị từ các FormControl
        for (const controlName in this.createForm.controls) {
            this.worker[controlName] = this.createForm.controls[controlName].value;
        }

        const formData = new FormData();
        formData.append(
            'worker',
            new Blob([JSON.stringify(this.worker)], {type: 'application/json'})
        );
        if (this.image) {
            formData.append('image', this.image);
        }

        console.log('worker sau khi sua/ them: ', this.worker);

        this.manageServiceWorkerService.save(formData).subscribe(
            (res) => {
                this.toast.success('Thành công');
                this.dialogRef.close({event: 'cancel'});
            }
        );
    }

    closeDialog() {
        this.dialogRef.close({event: 'cancel'});
    }

    onChangeFile(event) {
        this.image = event.target.files[0] as File;
    }
}
