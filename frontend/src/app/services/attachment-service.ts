import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api-service';
import { AttachmentRequest, AttachmentResponse } from '../models/attachment';

@Injectable({
  providedIn: 'root',
})
export class AttachmentService {
  private api = inject(ApiService);
  private url = 'attachments';

  getAll(): Observable<AttachmentResponse[]> {
    return this.api.get<AttachmentResponse[]>(this.url);
  }

  getById(id: number): Observable<AttachmentResponse> {
    return this.api.get<AttachmentResponse>(`${this.url}/${id}`);
  }

  create(body: AttachmentRequest): Observable<AttachmentResponse> {
    return this.api.post<AttachmentResponse>(this.url, body);
  }

  update(id: number, body: AttachmentRequest): Observable<AttachmentResponse> {
    return this.api.put<AttachmentResponse>(`${this.url}/${id}`, body);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.url}/${id}`);
  }
}
