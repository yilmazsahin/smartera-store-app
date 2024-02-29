import {Component, OnInit} from '@angular/core';
import {ApiService} from '../../services/api.service';
import {Customer} from '../../common/customer';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute} from '@angular/router';
import {of, tap} from 'rxjs';
import OauthUserService from '../../common/OauthUserService';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrl: './customer-profile.component.css',
})
export class CustomerProfileComponent implements OnInit {
  customerData: any;
  editedCustomer: any;
  customerEmail!: string;
  showEditForm!: boolean;

  constructor(
    private apiService: ApiService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private customer: Customer
  ) {
  }

  ngOnInit(): void {
    this.getCustomerData();
  }

  getCustomerData() {
    this.customerData = OauthUserService.getUser();
    console.log('Customer Data: ', this.customerData);
    if (this.customerData) {
      this.customerEmail = this.customerData.email;
      console.log('Kullanıcı e postası: ', this.customerEmail);
    } else {
      console.error('Customer data is null');
    }
  }

  logout() {
    this.authService.logout();
  }

  saveCustomerChanges() {
    console.log('Saving customer changes...');
    const customerId = this.editedCustomer.id; // veya uygun bir şekilde müşteri kimliğini belirleyin
    this.apiService.updateCustomer(this.editedCustomer).subscribe(
      () => {
        console.log('Customer updated successfully.');
        // İstendiği takdirde burada ek işlemler yapılabilir
      },
      (error) => {
        console.error('Error updating customer: ', error);
      }
    );
  }

  deleteCustomer(customerId: any) {
    console.log('Deleting customer with ID: ', customerId);
    this.apiService.deleteCustomer(customerId).subscribe(
      () => {
        console.log('Customer deleted successfully.');
      },
      (error) => {
        console.error('Error deleting customer: ', error);
      }
    );
  }

  editCustomer(customerId: any) {
    console.log('Editing customer with ID: ', customerId);

    this.apiService.getCustomerById(customerId).subscribe(
      (customer: any) => {
        console.log('Fetched customer details for editing: ', customer);
        this.editedCustomer = customer;
        this.showEditForm = true;
      },
      (error) => {
        console.error('Error fetching customer details for editing: ', error);
      }
    );
  }

  cancelEdit() {
    this.showEditForm = false;
  }
}
