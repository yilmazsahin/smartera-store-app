import { Product } from './../../common/product';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { AuthService } from '../../services/auth.service';
import { catchError, map, of } from 'rxjs';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css',
})
export class EditProductComponent implements OnInit {
  product: Product = new Product();
  productId = null;

  constructor(
    private apiService: ApiService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.productId = params['productId'];
    });
    this.loadProducts();
  }

  loadProducts() {
    this.apiService
      .getAllProducts()
      .pipe(
        map((products: Product[]) => {
          return products.filter((product) => {
            return product.id == this.productId;
          });
        }),
        catchError((error) => {
          console.error('Error loading products', error);
          return of(null);
        })
      )
      .subscribe((filteredProducts: Product[] | null) => {
        if (filteredProducts && filteredProducts.length > 0) {
          this.product = this.product = filteredProducts[0];
          console.log('Filtered product', this.product);
        } else {
          console.log(`Product with Id ${this.product.id} not found.`);
        }
      });
  }
  editProduct() {
    if (this.product) {
      debugger;
      this.apiService
        .updateProduct(this.product)
        .pipe(
          catchError((error) => {
            debugger;
            console.log('error updating product: ', error);
            return of(null);
          })
        )
        .subscribe((response) => {
          debugger;
          if (response) {
            debugger;
            console.log('Product updated succesFully: ', response);
            this.router.navigate(['product']);
          }
        });
    }
  }
  logout() {
    this.authService.logout();
  }
}
