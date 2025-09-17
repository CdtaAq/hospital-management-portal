import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../../../services/payment.service';
import { Payment } from '../../../core/models/payment.model';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html'
})
export class PaymentListComponent implements OnInit {
  payments: Payment[] = [];

  constructor(private svc: PaymentService) {}

  ngOnInit(): void {
    // demo: get payments for patientId=1
    this.svc.findByPatient(1).subscribe(p => this.payments = p);
  }

  markPaid(p: Payment) {
    if (!p.id) return;
    this.svc.markPaid(p.id).subscribe(() => p.status = 'PAID');
  }
}
