import { Component, inject } from '@angular/core';
import { CompaniesList } from '../../components/companies-list/companies-list';
import { CompaniesForm } from '../../components/companies-form/companies-form';
import { CompanyService } from '../../services/company-service';

@Component({
  selector: 'app-companies',
  imports: [
    CompaniesList,
    CompaniesForm
  ],
  templateUrl: './companies.html',
  styleUrl: './companies.scss',
})
export class Companies {
  companyService = inject(CompanyService);
}
