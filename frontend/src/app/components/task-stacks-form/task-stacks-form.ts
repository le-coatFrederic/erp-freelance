import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskStackService } from '../../services/task-stack-service';

@Component({
  selector: 'app-task-stacks-form',
  imports: [ReactiveFormsModule],
  templateUrl: './task-stacks-form.html',
  styleUrl: './task-stacks-form.scss',
})
export class TaskStacksForm {
  private fb = inject(FormBuilder);
  private service = inject(TaskStackService);

  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  form = this.fb.nonNullable.group({
    name: ['', [Validators.required, Validators.minLength(2)]],
    description: [''],
    isCompleted: [false],
  });

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    this.service.create(this.form.getRawValue()).subscribe({
      next: () => {
        this.success.set(true);
        this.form.reset();
      },
      error: err => this.error.set(err.message ?? 'Erreur'),
      complete: () => this.isSubmitting.set(false),
    });
  }
}
