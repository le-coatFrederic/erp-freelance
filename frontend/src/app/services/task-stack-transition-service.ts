import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api-service';
import { TaskStackTransitionRequest, TaskStackTransitionResponse } from '../models/task-stack-transition';

@Injectable({
  providedIn: 'root',
})
export class TaskStackTransitionService {
  private api = inject(ApiService);
  private url = 'task_stack_transitions';

  getAll(): Observable<TaskStackTransitionResponse[]> {
    return this.api.get<TaskStackTransitionResponse[]>(this.url);
  }

  getById(id: number): Observable<TaskStackTransitionResponse> {
    return this.api.get<TaskStackTransitionResponse>(`${this.url}/${id}`);
  }

  create(body: TaskStackTransitionRequest): Observable<TaskStackTransitionResponse> {
    return this.api.post<TaskStackTransitionResponse>(this.url, body);
  }

  update(id: number, body: TaskStackTransitionRequest): Observable<TaskStackTransitionResponse> {
    return this.api.put<TaskStackTransitionResponse>(`${this.url}/${id}`, body);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.url}/${id}`);
  }
}
