import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DoctorListComponent } from './components/doctor/doctor-list/doctor-list.component';
import { DoctorFormComponent } from './components/doctor/doctor-form/doctor-form.component';
import { DoctorDetailComponent } from './components/doctor/doctor-detail/doctor-detail.component';

import { DiseaseListComponent } from './components/disease/disease-list/disease-list.component';
import { DiseaseFormComponent } from './components/disease/disease-form/disease-form.component';

import { PatientListComponent } from './components/patient/patient-list/patient-list.component';
import { PatientFormComponent } from './components/patient/patient-form/patient-form.component';

import { AppointmentListComponent } from './components/appointment/appointment-list/appointment-list.component';
import { AppointmentFormComponent } from './components/appointment/appointment-form/appointment-form.component';

import { PaymentFormComponent } from './components/payment/payment-form/payment-form.component';
import { PaymentListComponent } from './components/payment/payment-list/payment-list.component';

import { SummaryViewComponent } from './components/summary/summary-view/summary-view.component';

const routes: Routes = [
  { path: 'doctors', component: DoctorListComponent },
  { path: 'doctors/new', component: DoctorFormComponent },
  { path: 'doctors/:id', component: DoctorDetailComponent },
  { path: 'diseases', component: DiseaseListComponent },
  { path: 'diseases/new', component: DiseaseFormComponent },
  { path: 'patients', component: PatientListComponent },
  { path: 'patients/new', component: PatientFormComponent },
  { path: 'appointments', component: AppointmentListComponent },
  { path: 'appointments/new', component: AppointmentFormComponent },
  { path: 'payments', component: PaymentListComponent },
  { path: 'payments/new', component: PaymentFormComponent },
  { path: 'summary', component: SummaryViewComponent },
  { path: '', redirectTo: '/doctors', pathMatch: 'full' },
  { path: '**', redirectTo: '/doctors' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
