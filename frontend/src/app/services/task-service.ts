import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api-service';
import { TaskRequest, TaskResponse, TaskMoveRequest } from '../models/task';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private api = inject(ApiService);
  private url = 'tasks';

  getAll(): Observable<TaskResponse[]> {
    return this.api.get<TaskResponse[]>(this.url);
  }

  getById(id: number): Observable<TaskResponse> {
    return this.api.get<TaskResponse>(`${this.url}/${id}`);
  }

  create(body: TaskRequest): Observable<TaskResponse> {
    return this.api.post<TaskResponse>(this.url, body);
  }

  update(id: number, body: TaskRequest): Observable<TaskResponse> {
    return this.api.put<TaskResponse>(`${this.url}/${id}`, body);
  }

  move(id: number, body: TaskMoveRequest): Observable<TaskResponse> {
    return this.api.post<TaskResponse>(`${this.url}/${id}/move`, body);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.url}/${id}`);
  }
}
