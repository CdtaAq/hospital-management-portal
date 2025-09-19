import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username = '';
  password = '';
  message = '';
  error = '';

  constructor(private authService: AuthService, private router: Router) {}

  onRegister() {
    this.authService.register(this.username, this.password).subscribe({
      next: (res: string) => {
        this.message = res;
        setTimeout(() => this.router.navigate(['/login']), 1500);
      },
      error: () => {
        this.error = 'Registration failed';
      }
    });
  }
}
