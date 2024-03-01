import {Router} from '@angular/router';
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../common/product';
import {ApiResponse} from '../../common/ApiResponse';
import {ApiService} from '../../services/api.service';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
})
export class ProductComponent implements OnInit {

  @Input() productList: Product[] = [];
  @Input() hideActions: boolean = false;
  @Output() onProductSelected = new EventEmitter<Product>();


  constructor(
    private apiService: ApiService,
    private authService: AuthService,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.loadPageData();
  }

  products: Product[] = [];

  deleteProduct(productId: number) {
    console.log(`Delete product ${productId}`);
    if (productId !== null) {
      this.apiService
        .deleteProduct(productId)
        .subscribe((response: ApiResponse<any>) => {
          if (response.errorMessage) {
            console.error(response.errorMessage);
          } else {
            console.log('Product deleted Successfuly', response);
            this.loadPageData();
          }
        });
    } else {
      console.error('Invalid product Id');
    }
  }

  loadPageData() {
    this.getProductList();
  }

  getProductList() {
    this.apiService.getAllProducts().subscribe((data) => {
      //console.log('Products: ' + JSON.stringify(data));
      this.products = data;
    });
  }

  editProduct(productId: number) {
    console.log(`Edit product ${productId}`);
    this.router.navigate([`/edit-product/${productId}`]);
  }

  logout() {
    this.authService.logout();
  }

  removeFromOrder(productId: number) {
  }

  addToOrder(product: Product) {
    this.onProductSelected.emit(product);
    console.log(product);
  }
}
