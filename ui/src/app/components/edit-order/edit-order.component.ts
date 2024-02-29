import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of, tap } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Order } from '../../common/order';
import { Customer } from '../../common/customer';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrl: './edit-order.component.css',
})
export class EditOrderComponent implements OnInit {
  order: Order = new Order();

  constructor(
    private apiService: ApiService,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      // Retrieve the orderId from the route parameters
      this.order.id = params['orderId'];
      console.log(`orderId: ${this.order.id}`);
    });

    this.loadOrders();
   // this.loadCustomers();
  }
/*
  loadCustomers() {
    this.apiService
      .getAllCustomers()
      .pipe(
        map((customers: Customer[]) => {
          console.log(customers);
          return customers.filter((customer) => {
            return customer.id == this.order.customerId;
          });
        }),
        catchError((error) => {
          console.error('Error loading Orders: ', error);
          return of(null);
        }),
        catchError((error) => {
          console.error('Error loading customers:', error);
          return of(null);
        })
      )
      .subscribe();
  }*/

  loadOrders() {
    this.apiService
      .getOrders()
      .pipe(
        map((orders: Order[]) => {
          console.log(orders);
          return orders.filter((order) => {
            return order.id == this.order.id;
          });
        }),
        catchError((error) => {
          console.error('Error loading Orders: ', error);
          return of(null);
        })
      )
      .subscribe((filteredOrders: Order[] | null) => {
        if (filteredOrders && filteredOrders.length > 0) {
          this.order = filteredOrders[0];
          console.log('Filtered Order:', this.order);
        } else {
          console.log('Order with ID 4 not found.');
        }
      });
  }
  editOrder() {
    this.apiService
      .updateOrder(this.order.id, this.order)
      .pipe(
        catchError((error) => {
          console.error('Error editing order: ', error);
          return of(null);
        })
      )
      .subscribe((response) => {
        if (response) {
          console.log('Order edited successfully: ', response);
          this.router.navigate(['orders']);
        }
      });
  }
  logout() {
    this.authService.logout();
  }
}
