import { Component } from '@angular/core';
import { PaymentService } from '../../../services/payment.service';
import { Payment } from '../../../core/models/payment.model';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html'
})
export class PaymentFormComponent {
  payment: Payment = { appointmentId: 0, amount: 0, status: 'PENDING' };
  message = '';

  constructor(private paymentService: PaymentService) {}

  submit(): void {
    this.paymentService.create(this.payment).subscribe(res => {
      this.message = `Payment created with id ${res.id}`;
    });
  }
}
