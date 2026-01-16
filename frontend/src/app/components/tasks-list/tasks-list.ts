import { Component, inject } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { TaskService } from '../../services/task-service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-tasks-list',
  imports: [RouterLink],
  templateUrl: './tasks-list.html',
  styleUrl: './tasks-list.scss',
})
export class TasksList {
  private service = inject(TaskService);
  tasks = toSignal(this.service.getAll(), { initialValue: [] });
}
