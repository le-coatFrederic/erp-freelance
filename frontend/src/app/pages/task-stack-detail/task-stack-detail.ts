import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskStackService } from '../../services/task-stack-service';
import { TaskStackResponse } from '../../models/task-stack';

@Component({
  selector: 'app-task-stack-detail',
  imports: [ReactiveFormsModule],
  templateUrl: './task-stack-detail.html',
  styleUrl: './task-stack-detail.scss',
})
export class TaskStackDetail {
  private route = inject(ActivatedRoute);
  private service = inject(TaskStackService);
  private fb = inject(FormBuilder);

  taskStack = signal<TaskStackResponse | null>(null);
  isLoading = signal(true);
  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  form = this.fb.nonNullable.group({
    name: ['', [Validators.required, Validators.minLength(2)]],
    description: [''],
    isCompleted: [false],
  });

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.loadTaskStack(id);
    }
  }

  loadTaskStack(id: number) {
    this.isLoading.set(true);
    this.service.getAll().subscribe({
      next: taskStacks => {
        const taskStack = taskStacks.find(ts => ts.id === id);
        if (taskStack) {
          this.taskStack.set(taskStack);
          this.form.patchValue(taskStack);
        }
        this.isLoading.set(false);
      },
      error: err => {
        this.error.set(err.message ?? 'Erreur de chargement');
        this.isLoading.set(false);
      },
    });
  }

  submit() {
    if (this.form.invalid || !this.taskStack()) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    const formValue = this.form.getRawValue();

    this.service.update(this.taskStack()!.id, formValue).subscribe({
      next: () => {
        this.success.set(true);
        setTimeout(() => this.success.set(false), 3000);
      },
      error: err => this.error.set(err.message ?? 'Erreur'),
      complete: () => this.isSubmitting.set(false),
    });
  }
}
