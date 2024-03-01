import {HttpClient} from '@angular/common/http';
import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ApiService} from '../../services/api.service';
import {Router} from '@angular/router';
import {catchError, of, tap} from 'rxjs';
import {AuthService} from '../../services/auth.service';
import {Product} from '../../common/product';
import {Order} from "../../common/order";
import {ProductComponent} from "../product/product.component";
import {Customer} from "../../common/customer";

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrl: './new-order.component.css',
})
export class NewOrderComponent implements OnInit {

  @Input() incomingOrder: Order = new Order();

  order: Order = new Order();
  selectedCustomerId: any;
  customers: Customer[] = [];
  customer: any = {};
  customerId: any;

  constructor(private apiService: ApiService, private authService: AuthService) {
  }

  ngOnInit(): void {

    if (this.incomingOrder.id != null) {
      console.log(this.incomingOrder);
      this.order = this.incomingOrder;
    }
    this.loadCustomers();
    this.loadProducts();

  }

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

  loadProducts() {
    this.apiService.getAllProducts().subscribe((products) => {
      // this.products = products;
    });
  }

  saveOrder() {
    this.order.customerId = this.selectedCustomerId;

    this.apiService 
      .createOrder(this.order)
      .pipe(
        catchError((error) => {
          console.error('Error adding Order: ', error);
          return of(null);
        })
      )
      .subscribe((response) => {
        console.log('Order added successfully: ', response);
        this.order = response;
      });
  }

  loguot() {
    this.authService.logout();
  }

  onProductSelected(product: Product) {
    console.log(`From Order ${product}`);
    product.size = 1;

    const exists = this.order.products.find(p => p.id === product.id);
    if (exists) {
      this.order.products = this.order.products.map(p => {
        if (p.id === product.id) {
          return {...p, size: p.size + 1}
        }
        return p;
      })
    } else {
      this.order.products = [...this.order.products, product];
    }
  }

  increaseProductSize(productId: number) {
    this.apiService.addProductToOrder(this.order.id, productId)
      .subscribe(data => {
        console.log(`addProductToOrder.data: ${data}`);
        this.order.products = this.order.products.map(product => {
          if (productId === product.id) {
            return {...product, size: product.size + 1}
          }
          return product;
        })
      })
  }

  decreaseProductSize(productId: number) {
    this.apiService.removeProductFromOrder(this.order.id, productId)
      .subscribe(data => {
        this.order.products = this.order.products
          .filter(product => {
            return productId !== product.id || product.size !== 1;
          }).map(product => {
            if (productId === product.id) {
              return {...product, size: product.size - 1}
            }
            return product;
          })
      })

  }
}
