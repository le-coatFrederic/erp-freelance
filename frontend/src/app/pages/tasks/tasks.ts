import { Component } from '@angular/core';
import { TasksList } from '../../components/tasks-list/tasks-list';
import { TasksForm } from '../../components/tasks-form/tasks-form';

@Component({
  selector: 'app-tasks',
  imports: [TasksList, TasksForm],
  templateUrl: './tasks.html',
  styleUrl: './tasks.scss',
})
export class Tasks {}
