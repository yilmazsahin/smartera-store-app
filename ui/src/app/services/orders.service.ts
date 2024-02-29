import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, map} from 'rxjs';
import Constants from "../common/Constants";
import {Order} from "../common/order";

@Injectable({
  providedIn: 'root',
})
export class OrdersService {

  constructor(private httpClient: HttpClient) {
  }

  getOrders(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(`${Constants.ORDER_SERVICE_URL}`)
  }
}
