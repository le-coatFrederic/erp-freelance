import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ContactService } from '../../services/contact-service';
import { CompanyService } from '../../services/company-service';
import { CompanyLight } from '../../models/company';

@Component({
  selector: 'app-contacts-form',
  imports: [ReactiveFormsModule],
  templateUrl: './contacts-form.html',
  styleUrl: './contacts-form.scss',
})
export class ContactsForm {
  private fb = inject(FormBuilder);
  private contactService = inject(ContactService);
  private companyService = inject(CompanyService);

  companies = signal<CompanyLight[]>([]);

  // signals UI
  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  form = this.fb.nonNullable.group({
    firstname: ['', [Validators.required, Validators.minLength(2)]],
    lastname: ['', [Validators.required, Validators.minLength(2)]],
    job: ['', [Validators.required]],
    email: ['', [Validators.email]],
    phone: [''],
    linkedin: ['', [Validators.required]],
    company_id: [0, Validators.required],
  });

  constructor() {
    // Charger les companies pour le select
    this.companyService.getLightCompanies().subscribe(list => this.companies.set(list));
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);
    this.success.set(false);

    this.contactService.createContact(this.form.getRawValue())
      .subscribe({
        next: () => {
          this.success.set(true);
          this.form.reset({
            firstname: '',
            lastname: '',
            job: '',
            email: '',
            phone: '',
            linkedin: '',
            company_id: 0
          });
        },
        error: err => this.error.set(err.message ?? 'Erreur lors de la création'),
        complete: () => this.isSubmitting.set(false)
      });
      
  }
}
