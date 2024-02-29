import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { catchError, filter, map, of, tap } from 'rxjs';
import { Customer } from '../../common/customer';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrl: './edit-customer.component.css',
})
export class EditCustomerComponent implements OnInit {
  customer: Customer = new Customer();
  customerId = null;

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router,
    private apiService: ApiService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.customerId = params['customerId'];
    });

    this.loadCustomers();
  }
  loadCustomers() {
    this.apiService
      .getAllCustomers()
      .pipe(
        map((customers: Customer[]) => {
          return customers.filter((customer) => {
            return customer.id == this.customerId;
          });
        }),
        catchError((error) => {
          console.error('Error loading customers:', error);
          return of(null);
        })
      )
      .subscribe((filteredCustomers: Customer[] | null) => {
        if (filteredCustomers && filteredCustomers.length > 0) {
          this.customer = filteredCustomers[0];
          console.log(`Filtered Customerr: `, this.customer);
        } else {
          console.log(`Order with ID 4 not found.`);
        }
      });
  }

  editCustomer() {
    if (this.customer) {
      this.apiService
        .updateCustomer(this.customer)
        .pipe(
          catchError((error) => {
            console.error('Error editing customer:', error);
            return of(null);
          })
        )
        .subscribe((response) => {
          if (response) {
            console.log('Customer editing successfully:', response);
            this.router.navigate(['customers']);
          }
        });
    }
  }

  logout() {
    this.authService.logout();
  }
}
