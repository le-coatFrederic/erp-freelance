import { inject, Injectable } from '@angular/core';
import { ApiService } from './api-service';
import { Observable } from 'rxjs';
import { ContactRequest, ContactResponse } from '../models/contact';

@Injectable({
  providedIn: 'root',
})
export class ContactService {
  private api = inject(ApiService);
  private url = 'contacts';

  getContacts(): Observable<ContactResponse[]> {
    return this.api.get<ContactResponse[]>(this.url);
  }

  getAvailableForTask(): Observable<ContactResponse[]> {
    return this.api.get<ContactResponse[]>(`${this.url}/available-for-task`);
  }

  createContact(body: ContactRequest): Observable<ContactResponse> {
    return this.api.post<ContactResponse>(this.url, body);
  }

  update(id: number, body: ContactRequest): Observable<ContactResponse> {
    return this.api.put<ContactResponse>(`${this.url}/${id}`, body);
  }
}
