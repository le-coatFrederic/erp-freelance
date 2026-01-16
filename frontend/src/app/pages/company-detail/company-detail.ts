import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CompanyService } from '../../services/company-service';
import { Company } from '../../models/company';
import { CompanyType, CompanyTypeList, CompanyTypeMapper } from '../../models/values/company-type';

@Component({
  selector: 'app-company-detail',
  imports: [ReactiveFormsModule],
  templateUrl: './company-detail.html',
  styleUrl: './company-detail.scss',
})
export class CompanyDetail {
  private route = inject(ActivatedRoute);
  private service = inject(CompanyService);
  private fb = inject(FormBuilder);

  company = signal<Company | null>(null);
  isLoading = signal(true);
  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  categories = CompanyTypeList;
  companyTypeMapper = CompanyTypeMapper;

  form = this.fb.nonNullable.group({
    name: ['', [Validators.required, Validators.minLength(2)]],
    siret: ['', [Validators.required, Validators.minLength(14), Validators.maxLength(14)]],
    category: [CompanyType.ESN, Validators.required],
    size: [1, [Validators.required, Validators.min(1)]],
  });

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.loadCompany(id);
    }
  }

  loadCompany(id: number) {
    this.isLoading.set(true);
    this.service.getCompanies().subscribe({
      next: companies => {
        const company = companies.find(c => c.id === id);
        if (company) {
          this.company.set(company);
          this.form.patchValue(company);
        }
        this.isLoading.set(false);
      },
      error: err => {
        this.error.set(err.message ?? 'Erreur de chargement');
        this.isLoading.set(false);
      },
    });
  }

  submit() {
    if (this.form.invalid || !this.company()) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    const updatedCompany = { ...this.form.getRawValue(), id: this.company()!.id };

    this.service.createCompany(updatedCompany).subscribe({
      next: () => {
        this.success.set(true);
        setTimeout(() => this.success.set(false), 3000);
      },
      error: err => this.error.set(err.message ?? 'Erreur'),
      complete: () => this.isSubmitting.set(false),
    });
  }
}
