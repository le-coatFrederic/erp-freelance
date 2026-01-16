import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AttachmentService } from '../../services/attachment-service';
import { AttachmentType, AttachmentTypeList, AttachmentTypeMapper } from '../../models/values/attachment-type';

@Component({
  selector: 'app-attachments-form',
  imports: [ReactiveFormsModule],
  templateUrl: './attachments-form.html',
  styleUrl: './attachments-form.scss',
})
export class AttachmentsForm {
  private fb = inject(FormBuilder);
  private service = inject(AttachmentService);

  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  types = AttachmentTypeList;
  typeMapper = AttachmentTypeMapper;

  form = this.fb.nonNullable.group({
    name: ['', [Validators.required, Validators.minLength(2)]],
    description: [''],
    type: [AttachmentType.PDF, Validators.required],
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
