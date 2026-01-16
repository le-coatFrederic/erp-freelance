import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ContactService } from '../../services/contact-service';
import { CompanyService } from '../../services/company-service';
import { ContactResponse } from '../../models/contact';
import { Company } from '../../models/company';

@Component({
  selector: 'app-contact-detail',
  imports: [ReactiveFormsModule],
  templateUrl: './contact-detail.html',
  styleUrl: './contact-detail.scss',
})
export class ContactDetail {
  private route = inject(ActivatedRoute);
  private contactService = inject(ContactService);
  private companyService = inject(CompanyService);
  private fb = inject(FormBuilder);

  contact = signal<ContactResponse | null>(null);
  companies = signal<Company[]>([]);
  isLoading = signal(true);
  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  form = this.fb.nonNullable.group({
    firstname: ['', [Validators.required, Validators.minLength(2)]],
    lastname: ['', [Validators.required, Validators.minLength(2)]],
    job: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    phone: [''],
    linkedin: [''],
    company_id: [0, Validators.required],
  });

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.loadContact(id);
      this.loadCompanies();
    }
  }

  loadContact(id: number) {
    this.isLoading.set(true);
    this.contactService.getContacts().subscribe({
      next: contacts => {
        const contact = contacts.find(c => c.id === id);
        if (contact) {
          this.contact.set(contact);
          this.form.patchValue({
            firstname: contact.firstname,
            lastname: contact.lastname,
            job: contact.job,
            email: contact.email,
            phone: contact.phone,
            linkedin: contact.linkedin,
            company_id: contact.company.id,
          });
        }
        this.isLoading.set(false);
      },
      error: err => {
        this.error.set(err.message ?? 'Erreur de chargement');
        this.isLoading.set(false);
      },
    });
  }

  loadCompanies() {
    this.companyService.getCompanies().subscribe({
      next: companies => this.companies.set(companies),
      error: err => this.error.set(err.message ?? 'Erreur de chargement des sociétés'),
    });
  }

  submit() {
    if (this.form.invalid || !this.contact()) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    const formValue = this.form.getRawValue();

    this.contactService.update(this.contact()!.id, formValue).subscribe({
      next: () => {
        this.success.set(true);
        setTimeout(() => this.success.set(false), 3000);
      },
      error: err => this.error.set(err.message ?? 'Erreur'),
      complete: () => this.isSubmitting.set(false),
    });
  }
}
