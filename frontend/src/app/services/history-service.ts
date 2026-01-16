import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api-service';
import { HistoryResponse } from '../models/history';

@Injectable({
  providedIn: 'root',
})
export class HistoryService {
  private api = inject(ApiService);
  private url = 'history';

  getAll(): Observable<HistoryResponse[]> {
    return this.api.get<HistoryResponse[]>(this.url);
  }

  getById(id: number): Observable<HistoryResponse> {
    return this.api.get<HistoryResponse>(`${this.url}/${id}`);
  }
}
