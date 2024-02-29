import {Order} from './../common/order';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, catchError, map, of, throwError} from 'rxjs';
import {AuthService} from './auth.service';
import {Customer} from '../common/customer';
import Constants from '../common/Constants';
import {NavigationExtras, Router} from '@angular/router';
import OauthUserService from '../common/OauthUserService';
import {Product} from '../common/product';
import {OrderProduct} from '../common/OrderProduct';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  ordersApiUrl = Constants.ORDER_SERVICE_URL;
  customersApiUrl = `${Constants.CUSTOMER_SERVICE_URL}`;
  productsApiUrl = `${Constants.PRODUCT_URL}`;
  orderProductsApiUrl = `${Constants.ORDER_PRODUCT_URL}`;
  httpClient: any;
  baseUrl: any;
  currentUser: any;

  headers = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*');

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {
    const user = OauthUserService.getUser();
    if (user) {
      this.headers.set('user-id', user.id);
    }
  }

  getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.customersApiUrl}`, {
      headers: this.headers,
    });
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.productsApiUrl}`);
  }

  getProductById(id: string): Observable<any> {
    return this.http.get(`${this.productsApiUrl}/${id}`);
  }

  updateProduct(product: Product): Observable<any> {
    return this.http.put(`${this.productsApiUrl}`, product);
  }

  updateCustomer(customer: Customer): Observable<any> {
    return this.http.put(`${this.customersApiUrl}`, customer);
  }

  deleteProduct(productId: number): Observable<any> {
    return this.http.delete(`${this.productsApiUrl}/${productId}`);
  }

  getProductsByOrderId(orderId: number): Observable<OrderProduct[]> {
    return this.http.get<OrderProduct[]>(
      `${this.orderProductsApiUrl}/${orderId}/products`
    );
  }

  getOrdersProducts(): Observable<OrderProduct[]> {
    return this.http.get<OrderProduct[]>(`${this.orderProductsApiUrl}`);
  }

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.ordersApiUrl}`);
  }

  createCustomer(customerData: any): Observable<any> {
    return this.http.post(`${this.customersApiUrl}`, customerData);
  }

  createProdcut(productData: any): Observable<any> {
    return this.http.post(`${this.productsApiUrl}`, productData);
  }

  getOrderById(orderId: number): Observable<any> {
    return this.http.get(`${this.ordersApiUrl}/${orderId}`);
  }

  deleteCustomer(customerId: string): Observable<any> {
    return this.http.delete(`${this.customersApiUrl}/${customerId}`);
  }

  getCustomerById(customerId: number): Observable<any> {
    return this.http.get(`${this.customersApiUrl}/${customerId}`);
  }

  createOrder(orderData: any): Observable<any> {
    return this.http.post(`${this.ordersApiUrl}`, orderData);
  }

  updateOrder(orderId: number, orderData: any): Observable<any> {
    return this.http.put(`${this.ordersApiUrl}/${orderId}`, orderData);
  }

  deleteOrder(orderId: number): Observable<any> {
    if (orderId == undefined) {
      console.error('Order ID is undefined');
      return of(null);
    } else return this.http.delete(`${this.ordersApiUrl}/${orderId}`);
  }

  getOrdersByCustomerId(customerId: string): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.ordersApiUrl}/getOrdersByCustomerId/${customerId}`
    );
  }

  getCustomerByEmail(customerEmail: string): Observable<any> {
    return this.http.get(
      `${this.customersApiUrl}/getCustomerByEmail/${customerEmail}`
    );
  }

  logout() {
    const navigationExtras: NavigationExtras = {
      replaceUrl: true,
    };

    this.router.navigate(['/login']);
  }

  addProductToOrder(orderId: number, productId: number): Observable<any[]> {
    return this.http.put<any[]>(`${this.ordersApiUrl}/${orderId}/${productId}`, {});
  }

  removeProductFromOrder(orderId: number, productId: number): Observable<any[]> {
    return this.http.delete<any[]>(`${this.ordersApiUrl}/${orderId}/${productId}`);
  }
}
