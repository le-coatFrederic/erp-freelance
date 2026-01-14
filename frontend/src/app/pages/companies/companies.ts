import { Component } from '@angular/core';
import { CompaniesList } from '../../components/companies-list/companies-list';
import { CompaniesForm } from '../../components/companies-form/companies-form';

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

}
