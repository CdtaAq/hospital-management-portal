export interface Payment {
  id?: number;
  appointmentId: number;
  amount: number;
  status: string;
}
