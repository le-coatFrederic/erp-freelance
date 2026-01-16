import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { toSignal } from '@angular/core/rxjs-interop';
import { TaskStackTransitionService } from '../../services/task-stack-transition-service';
import { TaskStackService } from '../../services/task-stack-service';
import { TransitionType, TransitionTypeList, TransitionTypeMapper } from '../../models/values/transition-type';

@Component({
  selector: 'app-task-stack-transitions-form',
  imports: [ReactiveFormsModule],
  templateUrl: './task-stack-transitions-form.html',
  styleUrl: './task-stack-transitions-form.scss',
})
export class TaskStackTransitionsForm {
  private fb = inject(FormBuilder);
  private service = inject(TaskStackTransitionService);
  private taskStackService = inject(TaskStackService);

  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  types = TransitionTypeList;
  typeMapper = TransitionTypeMapper;

  taskStacks = toSignal(this.taskStackService.getAll(), { initialValue: [] });

  form = this.fb.nonNullable.group({
    source_stack_id: [0, [Validators.required, Validators.min(1)]],
    destination_stack_id: [0, [Validators.required, Validators.min(1)]],
    transition_type: [TransitionType.MANUAL, Validators.required],
    auto_transition_threshold_hours: [null as number | null],
  });

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    const formValue = this.form.getRawValue();
    const payload = {
      ...formValue,
      auto_transition_threshold_hours: formValue.auto_transition_threshold_hours || undefined,
    };

    this.service.create(payload).subscribe({
      next: () => {
        this.success.set(true);
        this.form.reset();
      },
      error: err => this.error.set(err.message ?? 'Erreur'),
      complete: () => this.isSubmitting.set(false),
    });
  }
}
