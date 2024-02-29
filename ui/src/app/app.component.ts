import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ApiService } from './services/api.service';
import { CustomersService } from './services/customers.service';
import { Customer } from './common/customer';
import { Order } from './common/order';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  customers: Customer[] = [];
  orders: Order[] = [];
  title = 'Smartera';
  constructor(
    private apiService: ApiService,
    private customerService: CustomersService
  ) {}
  ngOnInit(): void {
    this.getCustomers();
    this.getOrders();
  }
  getCustomers() {
    this.customerService.getCustomers().subscribe((data: any) => {
      this.customers = data;
    });
  }
  getOrders() {
    this.apiService.getOrders().subscribe((data: any) => {
      this.orders = data;
    });
  }
}
