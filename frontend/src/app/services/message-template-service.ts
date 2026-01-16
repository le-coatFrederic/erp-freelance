import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api-service';
import { MessageTemplateRequest, MessageTemplateResponse } from '../models/message-template';

@Injectable({
  providedIn: 'root',
})
export class MessageTemplateService {
  private api = inject(ApiService);
  private url = 'message_templates';

  getAll(): Observable<MessageTemplateResponse[]> {
    return this.api.get<MessageTemplateResponse[]>(this.url);
  }

  getById(id: number): Observable<MessageTemplateResponse> {
    return this.api.get<MessageTemplateResponse>(`${this.url}/${id}`);
  }

  create(body: MessageTemplateRequest): Observable<MessageTemplateResponse> {
    return this.api.post<MessageTemplateResponse>(this.url, body);
  }

  update(id: number, body: MessageTemplateRequest): Observable<MessageTemplateResponse> {
    return this.api.put<MessageTemplateResponse>(`${this.url}/${id}`, body);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.url}/${id}`);
  }
}
