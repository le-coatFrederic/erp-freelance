import { inject, Injectable } from '@angular/core';
import { ApiService } from './api-service';
import { Observable } from 'rxjs';
import { ContactRequest, ContactResponse } from '../models/contact';

@Injectable({
  providedIn: 'root',
})
export class ContactService {
  private api = inject(ApiService);

  getContacts(): Observable<ContactResponse[]> {
    return this.api.get<ContactResponse[]>('contacts');
  }

  getAvailableForTask(): Observable<ContactResponse[]> {
    return this.api.get<ContactResponse[]>('contacts/available-for-task');
  }

  createContact(body: ContactRequest) {
    return this.api.post<ContactRequest>('contacts', body);
  }
}
