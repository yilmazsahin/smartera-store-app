import {ApiService} from './../../services/api.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Order} from '../../common/order';
import {OrdersService} from '../../services/orders.service';
import {AuthService} from '../../services/auth.service';
import {catchError, of} from 'rxjs';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css',
})
export class OrdersComponent implements OnInit {
  orders: Order[] = [];

  constructor(
    private ordersService: OrdersService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private apiService: ApiService
  ) {
  }

  ngOnInit(): void {
    this.ordersList();
    this.route.paramMap.subscribe(() => {
    });
  }

  ordersList() {
    this.ordersService.getOrders().subscribe((data) => {
      //console.log('Orders: ' + JSON.stringify(data));
      this.orders = data;
      this.orders = this.orders.map(order => {
        const productPrices = order.products.map(product => product.price);
        order.totalPrice = productPrices.reduce((acc, price) => acc + price, 0);
        return order;
      })
    });
  }

  editOrder(orderId: number) {
    console.log('edit order: ' + orderId);
    this.router.navigate([`/edit-order/${orderId}`], {});
  }

  deleteOrder(orderId: number) {
    // todo delete yap burda.
    console.log('delete order: ' + orderId);
    if (orderId !== null) {
      this.apiService.deleteOrder(orderId).pipe(
        catchError((error) => {
          console.error('Error for delete order: ', error);
          return of(null);
        })
      ).subscribe((response) => {
        if (response) {
          console.log('Order deleted succesfully: ', response);
          this.router.navigate(['/orders']);
        }
      });
    } else {
      console.error('Invelid order ID')
    }
  }

  logout() {
    this.authService.logout();
  }
}
