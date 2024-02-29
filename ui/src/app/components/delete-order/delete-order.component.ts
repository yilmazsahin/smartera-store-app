import { Router } from '@angular/router';
import { ApiService } from './../../services/api.service';
import { Component, OnInit } from '@angular/core';
import { catchError, of, tap } from 'rxjs';

@Component({
  selector: 'app-delete-order',
  templateUrl: './delete-order.component.html',
  styleUrl: './delete-order.component.css',
})
export class DeleteOrderComponent implements OnInit {
  selectedOrderId: any;
  orders: any[] = [];
  order: any = {};
  constructor(private apiService: ApiService, private router: Router) {}
  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders() {
    this.apiService
      .getOrders()
      .pipe(
        tap((data) => {
          this.orders = data;
        }),
        catchError((error) => {
          console.error('Error Loading orders', error);
          return of(null);
        })
      )
      .subscribe();
  }

  deleteOrder() {
    if (this.selectedOrderId !== null) {
      this.apiService
        .deleteOrder(this.selectedOrderId)
        .pipe(
          catchError((error) => {
            console.error('Error for delete order: ', error);
            return of(null);
          })
        )
        .subscribe((response) => {
          if (response) {
            console.log('Order deleted succesfully: ', response);
            this.router.navigate(['orders']);
          }
        });
    }
  }
}

