import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Companies } from './pages/companies/companies';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Companies],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('frontend');
}
