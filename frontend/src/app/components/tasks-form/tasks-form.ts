import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { toSignal } from '@angular/core/rxjs-interop';
import { TaskService } from '../../services/task-service';
import { MessageTemplateService } from '../../services/message-template-service';
import { ContactService } from '../../services/contact-service';
import { TaskStackService } from '../../services/task-stack-service';
import { TaskCategory, TaskCategoryList, TaskCategoryMapper } from '../../models/values/task-category';

@Component({
  selector: 'app-tasks-form',
  imports: [ReactiveFormsModule],
  templateUrl: './tasks-form.html',
  styleUrl: './tasks-form.scss',
})
export class TasksForm {
  private fb = inject(FormBuilder);
  private service = inject(TaskService);
  private messageTemplateService = inject(MessageTemplateService);
  private contactService = inject(ContactService);
  private taskStackService = inject(TaskStackService);

  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  categories = TaskCategoryList;
  categoryMapper = TaskCategoryMapper;

  messageTemplates = toSignal(this.messageTemplateService.getAll(), { initialValue: [] });
  contacts = toSignal(this.contactService.getAvailableForTask(), { initialValue: [] });
  taskStacks = toSignal(this.taskStackService.getAll(), { initialValue: [] });

  form = this.fb.nonNullable.group({
    message_template_id: [0, [Validators.required, Validators.min(1)]],
    contact_id: [0, [Validators.required, Validators.min(1)]],
    task_stack_id: [0, [Validators.required, Validators.min(1)]],
    name: ['', [Validators.required, Validators.minLength(2)]],
    description: [''],
    category: [TaskCategory.OTHER, Validators.required],
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
