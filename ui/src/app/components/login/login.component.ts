import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { HttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { Customer } from '../../common/customer';
import OauthUserService from '../../common/OauthUserService';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';
  authorizationLevel: string = '';
  constructor(
    private authService: AuthService,
    private router: Router,
    private http: HttpClient,
    private apiService: ApiService
  ) {}
  ngOnInit(): void {}

  login() {
    console.log('Login metoduna giriş yapti');
    this.authService
      .login(this.email, this.password)
      .subscribe((response: any) => {
        // response.body = customer

        //  todo gelen response'u kontrol et
        if (response != null && response.role) {
          // response.authorizationLevel ? "ADMIN "
          console.log('CUSTOMER' + response.role);
          this.handleSuccessfulLogin(response.role, response);
          OauthUserService.setUser(response);
          // response'dan kullanicinin rol''unu alacaksin
          console.log('Giriş başarılı');
        } else {
          // todo Kullanici Adi/Password yanlis oldugunda ekrana hata basilmali.
          console.log('Giriş BAŞARIZ');
          // Giriş başarısız, hata işlemleri burada yapılabilir
        }
        return true;
      });
    console.log(
      `Login attempt with email: ${this.email}, password: ${this.password}, authorizationLevel : ${this.authorizationLevel}`
    );
  }

  handleSuccessfulLogin(authorizationLevel: string, customerData: any) {
    if (authorizationLevel === 'ADMIN') {
      let result = this.router.navigate(['/customers']);
    } else if (authorizationLevel === 'CUSTOMER') {
      let result = this.router.navigate(['home-page'], {
        state: { customerData: customerData },
      });
    } else {
      console.log('Giriş işlemi başarısız. email veya şifre hatalı.');
    }
  }
  logout() {
    this.authService.logout();
  }
}
