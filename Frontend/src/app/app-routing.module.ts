import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorListComponent } from './components/doctor/doctor-list/doctor-list.component';
import { DiseaseListComponent } from './components/disease/disease-list/disease-list.component';
import { PatientListComponent } from './components/patient/patient-list/patient-list.component';
import { AppointmentListComponent } from './components/appointment/appointment-list/appointment-list.component';
import { PaymentFormComponent } from './components/payment/payment-form/payment-form.component';
import { SummaryViewComponent } from './components/summary/summary-view/summary-view.component';

const routes: Routes = [
  { path: 'doctors', component: DoctorListComponent },
  { path: 'diseases', component: DiseaseListComponent },
  { path: 'patients', component: PatientListComponent },
  { path: 'appointments', component: AppointmentListComponent },
  { path: 'payments', component: PaymentFormComponent },
  { path: 'summary', component: SummaryViewComponent },
  { path: '', redirectTo: '/doctors', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
