import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../common/customer';
import Constants from '../common/Constants';

@Injectable({
  providedIn: 'root',
})
export class CustomersService {
  constructor(private httpClient: HttpClient) {}

  getCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(`${Constants.CUSTOMER_SERVICE_URL}`);
  }
}
