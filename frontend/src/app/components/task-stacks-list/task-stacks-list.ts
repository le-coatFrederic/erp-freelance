import { Component, inject } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { TaskStackService } from '../../services/task-stack-service';

@Component({
  selector: 'app-task-stacks-list',
  imports: [RouterLink],
  templateUrl: './task-stacks-list.html',
  styleUrl: './task-stacks-list.scss',
})
export class TaskStacksList {
  private service = inject(TaskStackService);
  taskStacks = toSignal(this.service.getAll(), { initialValue: [] });
}
