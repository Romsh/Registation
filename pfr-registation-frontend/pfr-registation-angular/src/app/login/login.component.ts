import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuth } from '../models/user-auth';

import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide = true;
  user: UserAuth;

  lat = 50.693341;
  lng = 3.158566;

   constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.user = new UserAuth();
    this.user.username = '';
    this.user.password = '';
  }

  login() {
    this.authService.login(this.user).subscribe(res => {
      if (res) {
        console.log("login OOOOOOOk");
        this.router.navigateByUrl('/dashboard');
      }
    });
  }

}
