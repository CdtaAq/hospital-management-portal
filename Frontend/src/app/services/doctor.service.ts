import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../models/doctor';

@Injectable({ providedIn: 'root' })
export class DoctorService {
  private base = '/api/doctors';
  constructor(private http: HttpClient) {}

  list(specialty?: string): Observable<Doctor[]> {
    const params = specialty ? { specialty } : {};
    return this.http.get<Doctor[]>(this.base, { params });
  }
  get(id: number) { return this.http.get<Doctor>(`${this.base}/${id}`); }
  create(d: Doctor){ return this.http.post<Doctor>(this.base, d); }
  update(id:number, d:Doctor){ return this.http.put<Doctor>(`${this.base}/${id}`, d); }
  delete(id:number){ return this.http.delete(`${this.base}/${id}`); }
}
