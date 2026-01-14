import { Injectable } from '@angular/core';
import { Company } from '../models/company';
import { CompanyType } from '../models/values/company-type';
import { ApiService } from './api-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private api: ApiService) {}

  getCompanies(): Observable<Company[]> {
    return this.api.get<Company[]>('companies');
  }
}
