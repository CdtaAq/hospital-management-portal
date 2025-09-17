export interface Summary {
  patientId: number;
  appointments: any[];
  prescriptions: any[];
  admissions: any[];
  payments: any[];
  stats: {
    totalAppointments: number;
    totalPaid: number;
  };
}
