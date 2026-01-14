import { inject, Injectable } from '@angular/core';
import { Company, CompanyLight } from '../models/company';
import { CompanyType } from '../models/values/company-type';
import { ApiService } from './api-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  private api = inject(ApiService);
  private url = 'companies';

  getCompanies(): Observable<Company[]> {
    return this.api.get<Company[]>(this.url);
  }

  getLightCompanies(): Observable<CompanyLight[]> {
    return this.api.get<CompanyLight[]>(this.url + '/light');
  }

  createCompany(body: Company) {
    return this.api.post<Company>(this.url, body);
  }
}
