import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MessageTemplateService } from '../../services/message-template-service';
import { MessageTemplateType, MessageTemplateTypeList, MessageTemplateTypeMapper } from '../../models/values/message-template-type';

@Component({
  selector: 'app-message-templates-form',
  imports: [ReactiveFormsModule],
  templateUrl: './message-templates-form.html',
  styleUrl: './message-templates-form.scss',
})
export class MessageTemplatesForm {
  private fb = inject(FormBuilder);
  private service = inject(MessageTemplateService);

  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  types = MessageTemplateTypeList;
  typeMapper = MessageTemplateTypeMapper;

  form = this.fb.nonNullable.group({
    subject: ['', [Validators.required, Validators.minLength(2)]],
    description: [''],
    type: [MessageTemplateType.FIRST_CONTACT, Validators.required],
    content: ['', Validators.required],
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
