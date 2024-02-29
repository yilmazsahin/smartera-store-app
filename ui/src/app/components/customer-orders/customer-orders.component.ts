import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import OauthUserService from '../../common/OauthUserService';
import { ApiService } from '../../services/api.service';
import { Order } from '../../common/order';
import { catchError, of, pipe } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-orders',
  templateUrl: './customer-orders.component.html',
  styleUrl: './customer-orders.component.css',
})
export class CustomerOrdersComponent implements OnInit {
  orders: Order[] = [];
  customerId!: string;

  noOrdersExist!: boolean;
  @ViewChild('noOrdersTemplate') noOrdersTemplate!: TemplateRef<any>;

  constructor(
    private authService: AuthService,
    private apiService: ApiService,
    private router: Router,
    private order: Order
  ) {}

  ngOnInit(): void {
    const userData = OauthUserService.getUser();
    if (userData) {
      this.customerId = userData.id;
      this.getOrders();
    }
  }

  getOrders() {
    this.apiService.getOrdersByCustomerId(this.customerId)
    .pipe()
    .subscribe(
      (orders) => {
        console.log(orders)
        this.orders = orders;
        // this.noOrdersExist = this.orders.length === 0;
      }
    );
  }

  logout() {
    this.authService.logout();
  }

  editOrder(orderId: number) {
    throw new Error('Method not implemented.');
  }

  deleteOrder(orderId: number) {
    console.log('Order ID:', orderId);
    this.apiService
      .deleteOrder(orderId)
      .pipe(
        catchError((error) => {
          console.log('Order ID:', orderId);
          console.error('Error for delete order: ', error);
          return of(null);
        })
      )
      .subscribe((response) => {
        if (response) {
          console.log('Order deleted succesfully: ', response);
          this.router.navigate(['/customer-orders']);
        }
      });
  }
}
