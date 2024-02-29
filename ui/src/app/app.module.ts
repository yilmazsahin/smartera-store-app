import { CustomerOrdersComponent } from './components/customer-orders/customer-orders.component';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { CustomersComponent } from './components/customers/customers.component';
import { RouterModule, Routes } from '@angular/router';
import { OrdersComponent } from './components/orders/orders.component';
import { NewCustomerComponent } from './components/new-customer/new-customer.component';
import { EditCustomerComponent } from './components/edit-customer/edit-customer.component';
import { DeleteCustomerComponent } from './components/delete-customer/delete-customer.component';
import { NewOrderComponent } from './components/new-order/new-order.component';
import { EditOrderComponent } from './components/edit-order/edit-order.component';
import { DeleteOrderComponent } from './components/delete-order/delete-order.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { CustomerProfileComponent } from './components/customer-profile/customer-profile.component';
import { Customer } from './common/customer';
import { Order } from './common/order';
import { Product } from './common/product';
import { ProductComponent } from './components/product/product.component';
import { EditProductComponent } from './components/edit-product/edit-product.component';
import { NewProductComponent } from './components/new-product/new-product.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatButtonModule } from '@angular/material/button';
const routes: Routes = [
  { path: 'orders', component: OrdersComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'customers', component: CustomersComponent },
  { path: 'new-customer', component: NewCustomerComponent },
  { path: 'edit-customer/:customerId', component: EditCustomerComponent },
  { path: 'delete-customer', component: DeleteCustomerComponent },
  { path: 'new-order', component: NewOrderComponent },
  { path: 'edit-order/:orderId', component: EditOrderComponent },
  { path: 'delete-order', component: DeleteOrderComponent },
  { path: 'home-page', component: HomePageComponent },
  { path: 'customer-profile', component: CustomerProfileComponent },
  { path: 'customer-orders', component: CustomerOrdersComponent },
  { path: 'product', component: ProductComponent },
  { path: 'edit-product/:productId', component: EditProductComponent },
  { path: 'new-product', component: NewProductComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CustomersComponent,
    OrdersComponent,
    NewCustomerComponent,
    EditCustomerComponent,
    DeleteCustomerComponent,
    NewOrderComponent,
    EditOrderComponent,
    DeleteOrderComponent,
    HomePageComponent,
    CustomerProfileComponent,
    CustomerOrdersComponent,
    ProductComponent,
    EditProductComponent,
    NewProductComponent,
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  providers: [Customer, Order, Product],
  exports: [RouterModule],
  bootstrap: [AppComponent],
})
export class AppModule {}
