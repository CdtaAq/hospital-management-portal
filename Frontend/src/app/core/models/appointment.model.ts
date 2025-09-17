export interface Appointment {
  id?: number;
  patientId: number;
  doctorId: number;
  startTime: string;
  status?: string;
  emergency?: boolean;
}
