import { ApiService } from './../../services/api.service';
import { Customer } from './../../common/customer';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { catchError, of } from 'rxjs';
import OauthUserService from '../../common/OauthUserService';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrl: './new-customer.component.css',
})
export class NewCustomerComponent {
  customer: any = {};
  oauthUser: Customer | undefined;

  constructor(
    private router: Router,
    private apiService: ApiService,
    private authService: AuthService
  ) {
    this.oauthUser = OauthUserService.getUser();
  }

  saveCustomer() {
    this.apiService
      .createCustomer(this.customer)
      .pipe(
        catchError((error) => {
          console.error('Error adding customer:', error);
          return of(null);
        })
      )
      .subscribe((response) => {
        if (response) {
          console.log('Customer added successfully:', response);
          this.router.navigate(['customers']);
        }
      });
  }

  handleSuccesfulSavedCustomer() {
    let result = this.router.navigate(['customers']);
    console.log(result);
  }
  logout() {
    this.authService.logout();
  }
}
