import { Component } from '@angular/core';
import { TaskStacksList } from '../../components/task-stacks-list/task-stacks-list';
import { TaskStacksForm } from '../../components/task-stacks-form/task-stacks-form';

@Component({
  selector: 'app-task-stacks',
  imports: [TaskStacksList, TaskStacksForm],
  templateUrl: './task-stacks.html',
  styleUrl: './task-stacks.scss',
})
export class TaskStacks {}
