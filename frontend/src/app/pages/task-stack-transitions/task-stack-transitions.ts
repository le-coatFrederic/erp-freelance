import { Component } from '@angular/core';
import { TaskStackTransitionsList } from '../../components/task-stack-transitions-list/task-stack-transitions-list';
import { TaskStackTransitionsForm } from '../../components/task-stack-transitions-form/task-stack-transitions-form';

@Component({
  selector: 'app-task-stack-transitions',
  imports: [TaskStackTransitionsList, TaskStackTransitionsForm],
  templateUrl: './task-stack-transitions.html',
  styleUrl: './task-stack-transitions.scss',
})
export class TaskStackTransitions {}
