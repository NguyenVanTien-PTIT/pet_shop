import { OrderActionPopupComponent } from './admin/components/manage-orders/action-popup/order-action-popup.component';
import { OrderPageModule } from './shop/myprofile/shopping-cart/order-page.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shop/common/header/header.component';
import { HomeComponent } from './shop/home/home.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SidenavComponent } from './shop/common/sidenav/sidenav.component';
import { OwlModule } from 'ngx-owl-carousel';
import { NgxPaginationModule } from 'ngx-pagination';
import { ToastrModule } from 'ngx-toastr';
import {TokenInterceptorService} from './shop/services/token-interceptor.service';

import {
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatStepperModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,

} from '@angular/material';
import { SliderComponent } from './shop/common/slider/slider.component';
import { FooterComponent } from './shop/common/footer/footer.component';
import { ProductsComponent } from './shop/products/products.component';
import { SingleProductComponent } from './shop/single-product/single-product.component';
import { LoginComponent } from './shop/login/login.component';
import { ShoppingCartComponent } from './shop/myprofile/shopping-cart/shopping-cart.component';
import { MyprofileComponent } from './shop/myprofile/myprofile.component';
import { ProfileInformationComponent } from './shop/myprofile/profile-information/profile-information.component';
import { ManageAddressComponent } from './shop/myprofile/manage-address/manage-address.component';
import { SavedCardsComponent } from './shop/myprofile/saved-cards/saved-cards.component';
import { MyRewardsComponent } from './shop/myprofile/my-rewards/my-rewards.component';
import { ReviewsRatingComponent } from './shop/myprofile/reviews-rating/reviews-rating.component';
import { NotificationsComponent } from './shop/myprofile/notifications/notifications.component';
import { WishlistComponent } from './shop/myprofile/wishlist/wishlist.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './admin/components/dashboard/dashboard.component';
import { ManageOrdersComponent } from './admin/components/manage-orders/manage-orders.component';
import { ManageUsersComponent } from './admin/components/manage-users/manage-users.component';
import { ManageProductsComponent } from './admin/components/manage-products/manage-products.component';
import { PopupDeleteComponent } from './admin/components/manage-users/popup-delete/popup-delete.component';
import { ActionPopupComponent } from './admin/components/manage-users/action-popup/action-popup.component';
import { DeleteProductPopupComponent } from './admin/components/manage-products/delete-popup/delete-popup.component';
import { ActionProductPopupComponent } from './admin/components/manage-products/action-popup/action-popup.component';
import { CategoryActionPopupComponent } from './admin/components/manage-products/category-action-popup/category-action-popup.component';
import { CategoryDeletePopupComponent } from './admin/components/manage-products/category-delete-popup/category-delete-popup.component';
import { OrderDeletePopupComponent } from './admin/components/manage-orders/delete-popup/order-delete-popup.component';
import { CKEditorModule } from 'ngx-ckeditor';
import { MaterialFileInputModule } from 'ngx-material-file-input';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { OrderDetailComponent } from './admin/components/manage-orders/order-detail/order-detail.component';
import {NumberDirective} from './shop/common/directive/number-only.directive';
import { ContactComponent } from './shop/common/contact/contact.component';
import { ManageServiceWorkerComponent } from './admin/components/manage-service-worker/manage-service-worker.component';
import {ActionPopupWorkerComponent} from './admin/components/manage-service-worker/action-popup/action-popup.component';
import { ManageServiceComponent } from './admin/components/manage-service/manage-service.component';
import {ActionPopupServiceComponent} from './admin/components/manage-service/action-popup/action-popup.component';
import { ServicePageComponent } from './shop/service-page/service-page.component';
import { SingleServiceComponent } from './shop/single-service/single-service.component';
import { BookingServiceComponent } from './shop/booking-service/booking-service.component';
import { ChooseWorkerPopupComponent } from './shop/booking-service/choose-worker-popup/choose-worker-popup.component';
import { WorkerDetailsComponent } from './shop/booking-service/worker-details/worker-details.component';
import {DatePipe} from '@angular/common';
import { ThanksBookingComponent } from './shop/thanks-booking/thanks-booking.component';
import { HistoryBookingComponent } from './shop/booking-service/history-booking/history-booking.component';
import { ManageBookingComponent } from './admin/components/manage-booking/manage-booking.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        HomeComponent,
        SidenavComponent,
        SliderComponent,
        FooterComponent,
        ProductsComponent,
        SingleProductComponent,
        LoginComponent,
        ShoppingCartComponent,
        MyprofileComponent,
        ProfileInformationComponent,
        ManageAddressComponent,
        SavedCardsComponent,
        MyRewardsComponent,
        ReviewsRatingComponent,
        NotificationsComponent,
        WishlistComponent,
        DashboardComponent,
        ManageOrdersComponent,
        ManageUsersComponent,
        ManageProductsComponent,
        PopupDeleteComponent,
        ActionPopupComponent,
        DeleteProductPopupComponent,
        ActionProductPopupComponent,
        CategoryActionPopupComponent,
        CategoryDeletePopupComponent,
        OrderDeletePopupComponent,
        OrderActionPopupComponent,
        OrderDetailComponent,
        NumberDirective,
        ContactComponent,
        ManageServiceWorkerComponent,
        ActionPopupWorkerComponent,
        ManageServiceComponent,
        ActionPopupServiceComponent,
        ServicePageComponent,
        SingleServiceComponent,
        BookingServiceComponent,
        ChooseWorkerPopupComponent,
        WorkerDetailsComponent,
        ThanksBookingComponent,
        HistoryBookingComponent,
        ManageBookingComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatIconModule,
        HttpClientModule,
        // Material
        MatAutocompleteModule,
        MatButtonModule,
        MatButtonToggleModule,
        MatCardModule,
        MatCheckboxModule,
        MatChipsModule,
        MatDatepickerModule,
        MatDialogModule,
        MatExpansionModule,
        MatGridListModule,
        MatIconModule,
        MatInputModule,
        MatListModule,
        MatMenuModule,
        MatProgressBarModule,
        MatProgressSpinnerModule,
        MatRadioModule,
        MatRippleModule,
        MatSelectModule,
        MatSidenavModule,
        MatSlideToggleModule,
        MatSliderModule,
        MatSnackBarModule,
        MatStepperModule,
        MatTableModule,
        MatTabsModule,
        MatToolbarModule,
        MatTooltipModule,
        MatNativeDateModule,
        OwlModule,
        MatTreeModule,
        ReactiveFormsModule,
        NgxPaginationModule,
        ToastrModule.forRoot({
            timeOut: 500,
            progressAnimation: 'increasing',
        }),
        OrderPageModule,
        CKEditorModule,
        MaterialFileInputModule,
        NgxChartsModule,
    ],
    providers: [
        DatePipe,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptorService,
            multi: true,
        },

    ],
    bootstrap: [AppComponent],
    exports: [
        NumberDirective
    ],
    entryComponents: [LoginComponent,
        PopupDeleteComponent,
        ActionPopupComponent,
        ActionProductPopupComponent,
        DeleteProductPopupComponent,
        CategoryActionPopupComponent,
        CategoryDeletePopupComponent,
        OrderDeletePopupComponent,
        OrderActionPopupComponent,
        OrderDetailComponent,
        ActionPopupWorkerComponent,
        ActionPopupServiceComponent,
        ChooseWorkerPopupComponent,
        WorkerDetailsComponent
    ]

})
export class AppModule {

}
