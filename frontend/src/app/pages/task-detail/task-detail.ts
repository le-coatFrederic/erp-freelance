import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskService } from '../../services/task-service';
import { MessageTemplateService } from '../../services/message-template-service';
import { ContactService } from '../../services/contact-service';
import { TaskStackService } from '../../services/task-stack-service';
import { TaskResponse } from '../../models/task';
import { MessageTemplateResponse } from '../../models/message-template';
import { ContactResponse } from '../../models/contact';
import { TaskStackResponse } from '../../models/task-stack';
import { TaskCategory, TaskCategoryList, TaskCategoryMapper } from '../../models/values/task-category';

@Component({
  selector: 'app-task-detail',
  imports: [ReactiveFormsModule],
  templateUrl: './task-detail.html',
  styleUrl: './task-detail.scss',
})
export class TaskDetail {
  private route = inject(ActivatedRoute);
  private taskService = inject(TaskService);
  private messageTemplateService = inject(MessageTemplateService);
  private contactService = inject(ContactService);
  private taskStackService = inject(TaskStackService);
  private fb = inject(FormBuilder);

  task = signal<TaskResponse | null>(null);
  messageTemplates = signal<MessageTemplateResponse[]>([]);
  contacts = signal<ContactResponse[]>([]);
  taskStacks = signal<TaskStackResponse[]>([]);
  isLoading = signal(true);
  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  categories = TaskCategoryList;
  categoryMapper = TaskCategoryMapper;

  form = this.fb.nonNullable.group({
    name: ['', [Validators.required, Validators.minLength(2)]],
    description: [''],
    category: [TaskCategory.CALL, Validators.required],
    message_template_id: [0, Validators.required],
    contact_id: [0, Validators.required],
    task_stack_id: [0, Validators.required],
  });

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.loadTask(id);
      this.loadRelatedData();
    }
  }

  loadTask(id: number) {
    this.isLoading.set(true);
    this.taskService.getAll().subscribe({
      next: tasks => {
        const task = tasks.find(t => t.id === id);
        if (task) {
          this.task.set(task);
          this.form.patchValue({
            name: task.name,
            description: task.description,
            category: task.category,
            message_template_id: task.message_template.id,
            contact_id: task.contact.id,
            task_stack_id: task.task_stack.id,
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

  loadRelatedData() {
    this.messageTemplateService.getAll().subscribe({
      next: templates => this.messageTemplates.set(templates),
      error: err => this.error.set(err.message ?? 'Erreur de chargement des modèles'),
    });

    this.contactService.getContacts().subscribe({
      next: contacts => this.contacts.set(contacts),
      error: err => this.error.set(err.message ?? 'Erreur de chargement des contacts'),
    });

    this.taskStackService.getAll().subscribe({
      next: stacks => this.taskStacks.set(stacks),
      error: err => this.error.set(err.message ?? 'Erreur de chargement des stacks'),
    });
  }

  submit() {
    if (this.form.invalid || !this.task()) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    const formValue = this.form.getRawValue();
    const updatedTask = {
      name: formValue.name,
      description: formValue.description,
      category: formValue.category,
      message_template_id: formValue.message_template_id,
      contact_id: formValue.contact_id,
      task_stack_id: formValue.task_stack_id,
    };

    this.taskService.update(this.task()!.id, updatedTask).subscribe({
      next: () => {
        this.success.set(true);
        setTimeout(() => this.success.set(false), 3000);
      },
      error: err => this.error.set(err.message ?? 'Erreur'),
      complete: () => this.isSubmitting.set(false),
    });
  }
}
