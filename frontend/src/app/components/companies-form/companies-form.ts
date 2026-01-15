import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CompanyService } from '../../services/company-service';
import { CompanyType, CompanyTypeMapper } from '../../models/values/company-type';

@Component({
  selector: 'app-companies-form',
  imports: [ReactiveFormsModule],
  templateUrl: './companies-form.html',
  styleUrl: './companies-form.scss',
})
export class CompaniesForm {
  private fb = inject(FormBuilder);
  private companyService = inject(CompanyService);

  categories = Object.values(CompanyType);
  companyTypeMapper = CompanyTypeMapper;

  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  form = this.fb.nonNullable.group({
    name: ['', [Validators.required, Validators.minLength(2)]],
    siret: ['', [Validators.required, Validators.minLength(4)]],
    category: [CompanyType.ESN, Validators.required],
    size: [1, [Validators.required, Validators.min(1)]],
  });

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    this.companyService.createCompany(this.form.getRawValue()).subscribe({
      next: () => {
        this.success.set(true);
        this.form.reset({
          name: '',
          siret: '',
          category: CompanyType.ESN,
          size: 0
        });
      },
      error: err => {
        this.error.set(err.message ?? 'Erreur lors de la création');
      },
      complete: () => this.isSubmitting.set(false)
    });
  }
}
