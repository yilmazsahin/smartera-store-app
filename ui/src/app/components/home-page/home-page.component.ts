import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css',
})
export class HomePageComponent implements OnInit {
  customerEmail!: string;
  constructor(private apiservice: ApiService) {}

  logout() {
    this.apiservice.logout();
  }
  ngOnInit(): void {
    this.customerEmail = '';
  }
}
