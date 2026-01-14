import { Component, inject, Input, input } from '@angular/core';
import { Company } from '../../models/company';
import { Observable } from 'rxjs';
import { NgForOf } from "../../../../node_modules/@angular/common/types/_common_module-chunk";
import { CompanyService } from '../../services/company-service';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-companies-list',
  imports: [],
  templateUrl: './companies-list.html',
  styleUrl: './companies-list.scss',
})
export class CompaniesList {
  private companyService = inject(CompanyService);
  companies = toSignal(this.companyService.getCompanies(), { initialValue: [] });
}
