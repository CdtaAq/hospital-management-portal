import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { DoctorListComponent } from './components/doctor/doctor-list/doctor-list.component';
import { DoctorFormComponent } from './components/doctor/doctor-form/doctor-form.component';
import { DiseaseListComponent } from './components/disease/disease-list/disease-list.component';
import { PatientListComponent } from './components/patient/patient-list/patient-list.component';
import { AppointmentListComponent } from './components/appointment/appointment-list/appointment-list.component';
import { PaymentFormComponent } from './components/payment/payment-form/payment-form.component';
import { SummaryViewComponent } from './components/summary/summary-view/summary-view.component';

@NgModule({
  declarations: [
    AppComponent,
    DoctorListComponent,
    DoctorFormComponent,
    DiseaseListComponent,
    PatientListComponent,
    AppointmentListComponent,
    PaymentFormComponent,
    SummaryViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
