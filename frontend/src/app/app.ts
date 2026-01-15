import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Companies } from './pages/companies/companies';
import { Contacts } from './pages/contacts/contacts';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Companies, Contacts],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('frontend');
}
