import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api-service';
import { TaskStackRequest, TaskStackResponse } from '../models/task-stack';

@Injectable({
  providedIn: 'root',
})
export class TaskStackService {
  private api = inject(ApiService);
  private url = 'task_stacks';

  getAll(): Observable<TaskStackResponse[]> {
    return this.api.get<TaskStackResponse[]>(this.url);
  }

  getById(id: number): Observable<TaskStackResponse> {
    return this.api.get<TaskStackResponse>(`${this.url}/${id}`);
  }

  create(body: TaskStackRequest): Observable<TaskStackResponse> {
    return this.api.post<TaskStackResponse>(this.url, body);
  }

  update(id: number, body: TaskStackRequest): Observable<TaskStackResponse> {
    return this.api.put<TaskStackResponse>(`${this.url}/${id}`, body);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.url}/${id}`);
  }
}
