import { catchError, of } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { AuthService } from '../../services/auth.service';
import { Product } from '../../common/product';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-new-product',

  templateUrl: './new-product.component.html',
  styleUrl: './new-product.component.css',
})
export class NewProductComponent implements OnInit {
  product: any = { name: '', price: '' };
  productForm!: FormGroup;
  logout() {
    this.authService.logout();
  }
  constructor(
    private apiService: ApiService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.productForm = new FormGroup({
      name: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
    });
  }
  saveProduct() {
    if (this.productForm.valid) {
      this.apiService
        .createProdcut(this.product)
        .pipe(
          catchError((error) => {
            console.error('Error adding product: ', error);
            return of(null);
          })
        )
        .subscribe((response) => {
          if (response) {
            console.log('Prodcut added successfuly: ', response);
            this.router.navigate(['product']);
          }
        });
    } else {
      console.log('Form is invalid. Cannot save product.');
    }
  }
}
