import { Component } from '@angular/core';
import { ContactsList } from '../../components/contacts-list/contacts-list';
import { ContactsForm } from '../../components/contacts-form/contacts-form';

@Component({
  selector: 'app-contacts',
  imports: [
    ContactsList,
    ContactsForm
  ],
  templateUrl: './contacts.html',
  styleUrl: './contacts.scss',
})
export class Contacts {

}
