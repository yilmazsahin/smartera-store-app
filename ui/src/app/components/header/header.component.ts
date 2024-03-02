import {Component, Input} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {ApiService} from "../../services/api.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  @Input() pageTitle: string = '';

  constructor(private authService: AuthService) {
  }

  logout() {
    this.authService.logout();
  }
}
