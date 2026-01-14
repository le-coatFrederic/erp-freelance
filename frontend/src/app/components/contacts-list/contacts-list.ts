import { Component, inject } from '@angular/core';
import { ContactService } from '../../services/contact-service';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-contacts-list',
  imports: [],
  templateUrl: './contacts-list.html',
  styleUrl: './contacts-list.scss',
})
export class ContactsList {
    private contactService = inject(ContactService);
    contacts = toSignal(this.contactService.getContacts(), { initialValue: [] });

}
