import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../../../services/payment.service';
import { loadStripe } from '@stripe/stripe-js';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css']
})
export class PaymentFormComponent implements OnInit {
  stripePromise = loadStripe(''); // publishable key will be set dynamically
  clientSecret = '';
  amount = 5000; // example: $50.00

  constructor(private paymentService: PaymentService) {}

  ngOnInit() {
    this.paymentService.createPaymentIntent(this.amount).subscribe(async (res) => {
      this.clientSecret = res.clientSecret;
      this.stripePromise = loadStripe(res.publishableKey);
    });
  }

  async pay() {
    const stripe = await this.stripePromise;
    if (!stripe || !this.clientSecret) return;

    const { error } = await stripe.confirmCardPayment(this.clientSecret, {
      payment_method: {
        card: { token: 'tok_visa' } // test token (replace with real card element)
      }
    });

    if (error) {
      alert('Payment failed: ' + error.message);
    } else {
      alert('Payment successful!');
    }
  }
}
