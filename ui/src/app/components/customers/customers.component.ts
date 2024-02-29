import {ApiService} from './../../services/api.service';
import {Component, OnInit} from '@angular/core';
import {CustomersService} from '../../services/customers.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Customer} from '../../common/customer';
import {AuthService} from '../../services/auth.service';
import {catchError, of} from 'rxjs';
import {ApiResponse} from "../../common/ApiResponse";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css',
})
export class CustomersComponent implements OnInit {
  showCustomerTable: any;
  customers: Customer[] = [];


  constructor(
    private customerService: CustomersService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private apiService: ApiService
  ) {
  }

  ngOnInit(): void {
    this.loadPageData();
  }

  loadPageData() {
    this.customerList();
  }

  customerList() {
    this.customerService.getCustomers().subscribe((data) => {
      console.log('Customers: ' + JSON.stringify(data));
      this.customers = data;
    });
  }

  logout() {
    this.authService.logout();
  }

  deleteCustomer(customerId: string) {
    console.log(`Delete customer ${customerId}`);
    if (customerId !== null) {
      this.apiService
        .deleteCustomer(customerId)
        .subscribe((response: ApiResponse<any>) => {
          if (response.errorMessage) {
            console.error(response.errorMessage);
          } else {
            console.log('Customer  deleted Succesfully: ', response);
            this.loadPageData();
          }
        });
    } else {
      console.error('Invalid customer Id');
    }
  }

  editCustomer(customerId: string) {
    console.log(`Edit Customer ${customerId}`);
    this.router.navigate([`/edit-customer/${customerId}`], {});
  }
}
