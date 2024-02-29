import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { catchError, of, tap } from 'rxjs';

@Component({
  selector: 'app-delete-customer',
  templateUrl: './delete-customer.component.html',
  styleUrl: './delete-customer.component.css',
})
export class DeleteCustomerComponent implements OnInit {
  customers: any[] = [];
  customer: any = {};
  selectedCustomerId: any;
  ngOnInit(): void {
    this.loadCustomers();
  }
  constructor(
    private apiService: ApiService,
    private router: Router
  ) {}
  loadCustomers() {
    this.apiService
      .getAllCustomers()
      .pipe(
        tap((data) => {
          this.customers = data;
        }),
        catchError((error) => {
          console.error('Error loading customers:', error);
          return of(null);
        })
      )
      .subscribe(); 
  }
  deleteCustomer() {
    if (this.selectedCustomerId !== null) {
      this.apiService
        .deleteCustomer(this.selectedCustomerId)
        .pipe(
          catchError((error) => {
            console.error('Error for delete Customer Id: ', error);
            return of(null);
          })
        )
        .subscribe((response) => {
          if (response) {
            console.log('Customer deleted successfully: ', response);
            this.router.navigate(['/customers']);
          }
        });
    }
  }
}

