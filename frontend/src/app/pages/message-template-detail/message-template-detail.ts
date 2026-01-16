import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MessageTemplateService } from '../../services/message-template-service';
import { MessageTemplateResponse } from '../../models/message-template';
import { MessageTemplateType, MessageTemplateTypeList, MessageTemplateTypeMapper } from '../../models/values/message-template-type';

@Component({
  selector: 'app-message-template-detail',
  imports: [ReactiveFormsModule],
  templateUrl: './message-template-detail.html',
  styleUrl: './message-template-detail.scss',
})
export class MessageTemplateDetail {
  private route = inject(ActivatedRoute);
  private service = inject(MessageTemplateService);
  private fb = inject(FormBuilder);

  messageTemplate = signal<MessageTemplateResponse | null>(null);
  isLoading = signal(true);
  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  templateTypes = MessageTemplateTypeList;
  templateTypeMapper = MessageTemplateTypeMapper;

  form = this.fb.nonNullable.group({
    subject: ['', [Validators.required, Validators.minLength(2)]],
    description: [''],
    type: [MessageTemplateType.FIRST_CONTACT, Validators.required],
    content: ['', Validators.required],
  });

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.loadMessageTemplate(id);
    }
  }

  loadMessageTemplate(id: number) {
    this.isLoading.set(true);
    this.service.getAll().subscribe({
      next: templates => {
        const template = templates.find(t => t.id === id);
        if (template) {
          this.messageTemplate.set(template);
          this.form.patchValue(template);
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
    if (this.form.invalid || !this.messageTemplate()) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    const formValue = this.form.getRawValue();

    this.service.update(this.messageTemplate()!.id, formValue).subscribe({
      next: () => {
        this.success.set(true);
        setTimeout(() => this.success.set(false), 3000);
      },
      error: err => this.error.set(err.message ?? 'Erreur'),
      complete: () => this.isSubmitting.set(false),
    });
  }
}
