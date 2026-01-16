import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskStackTransitionService } from '../../services/task-stack-transition-service';
import { TaskStackService } from '../../services/task-stack-service';
import { TaskStackTransitionResponse } from '../../models/task-stack-transition';
import { TaskStackResponse } from '../../models/task-stack';
import { TransitionType, TransitionTypeList, TransitionTypeMapper } from '../../models/values/transition-type';

@Component({
  selector: 'app-task-stack-transition-detail',
  imports: [ReactiveFormsModule],
  templateUrl: './task-stack-transition-detail.html',
  styleUrl: './task-stack-transition-detail.scss',
})
export class TaskStackTransitionDetail {
  private route = inject(ActivatedRoute);
  private service = inject(TaskStackTransitionService);
  private taskStackService = inject(TaskStackService);
  private fb = inject(FormBuilder);

  transition = signal<TaskStackTransitionResponse | null>(null);
  taskStacks = signal<TaskStackResponse[]>([]);
  isLoading = signal(true);
  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  transitionTypes = TransitionTypeList;
  transitionTypeMapper = TransitionTypeMapper;

  form = this.fb.nonNullable.group({
    source_stack_id: [0, Validators.required],
    destination_stack_id: [0, Validators.required],
    transition_type: [TransitionType.MANUAL, Validators.required],
    auto_transition_threshold_hours: [0],
  });

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.loadTransition(id);
      this.loadTaskStacks();
    }
  }

  loadTransition(id: number) {
    this.isLoading.set(true);
    this.service.getAll().subscribe({
      next: transitions => {
        const transition = transitions.find(t => t.id === id);
        if (transition) {
          this.transition.set(transition);
          this.form.patchValue({
            source_stack_id: transition.source_stack.id,
            destination_stack_id: transition.destination_stack.id,
            transition_type: transition.transition_type,
            auto_transition_threshold_hours: transition.auto_transition_threshold_hours,
          });
        }
        this.isLoading.set(false);
      },
      error: err => {
        this.error.set(err.message ?? 'Erreur de chargement');
        this.isLoading.set(false);
      },
    });
  }

  loadTaskStacks() {
    this.taskStackService.getAll().subscribe({
      next: stacks => this.taskStacks.set(stacks),
      error: err => this.error.set(err.message ?? 'Erreur de chargement des stacks'),
    });
  }

  submit() {
    if (this.form.invalid || !this.transition()) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    const formValue = this.form.getRawValue();

    this.service.update(this.transition()!.id, formValue).subscribe({
      next: () => {
        this.success.set(true);
        setTimeout(() => this.success.set(false), 3000);
      },
      error: err => this.error.set(err.message ?? 'Erreur'),
      complete: () => this.isSubmitting.set(false),
    });
  }
}
