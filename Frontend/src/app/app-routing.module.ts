import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// Doctor
import { DoctorListComponent } from './components/doctor/doctor-list/doctor-list.component';
import { DoctorFormComponent } from './components/doctor/doctor-form/doctor-form.component';
import { DoctorDetailComponent } from './components/doctor/doctor-detail/doctor-detail.component';

// Disease
import { DiseaseListComponent } from './components/disease/disease-list/disease-list.component';
import { DiseaseFormComponent } from './components/disease/disease-form/disease-form.component';

// Patient
import { PatientListComponent } from './components/patient/patient-list/patient-list.component';
import { PatientFormComponent } from './components/patient/patient-form/patient-form.component';

// Appointment
import { AppointmentListComponent } from './components/appointment/appointment-list/appointment-list.component';
import { AppointmentFormComponent } from './components/appointment/appointment-form/appointment-form.component';

// Payment
import { PaymentFormComponent } from './components/payment/payment-form/payment-form.component';
import { PaymentListComponent } from './components/payment/payment-list/payment-list.component';

// Summary
import { SummaryViewComponent } from './components/summary/summary-view/summary-view.component';

@NgModule({
  declarations: [
    AppComponent,
    DoctorListComponent,
    DoctorFormComponent,
    DoctorDetailComponent,
    DiseaseListComponent,
    DiseaseFormComponent,
    PatientListComponent,
    PatientFormComponent,
    AppointmentListComponent,
    AppointmentFormComponent,
    PaymentFormComponent,
    PaymentListComponent,
    SummaryViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
