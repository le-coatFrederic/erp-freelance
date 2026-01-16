import { Component, inject } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { TaskStackTransitionService } from '../../services/task-stack-transition-service';
import { TransitionTypeMapper } from '../../models/values/transition-type';

@Component({
  selector: 'app-task-stack-transitions-list',
  imports: [RouterLink],
  templateUrl: './task-stack-transitions-list.html',
  styleUrl: './task-stack-transitions-list.scss',
})
export class TaskStackTransitionsList {
  private service = inject(TaskStackTransitionService);
  transitions = toSignal(this.service.getAll(), { initialValue: [] });
  typeMapper = TransitionTypeMapper;
}
