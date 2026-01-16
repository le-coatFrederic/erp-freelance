import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AttachmentService } from '../../services/attachment-service';
import { AttachmentResponse } from '../../models/attachment';
import { AttachmentType, AttachmentTypeList, AttachmentTypeMapper } from '../../models/values/attachment-type';

@Component({
  selector: 'app-attachment-detail',
  imports: [ReactiveFormsModule],
  templateUrl: './attachment-detail.html',
  styleUrl: './attachment-detail.scss',
})
export class AttachmentDetail {
  private route = inject(ActivatedRoute);
  private service = inject(AttachmentService);
  private fb = inject(FormBuilder);

  attachment = signal<AttachmentResponse | null>(null);
  isLoading = signal(true);
  isSubmitting = signal(false);
  error = signal<string | null>(null);
  success = signal(false);

  attachmentTypes = AttachmentTypeList;
  attachmentTypeMapper = AttachmentTypeMapper;

  form = this.fb.nonNullable.group({
    name: ['', [Validators.required, Validators.minLength(2)]],
    description: [''],
    type: [AttachmentType.PDF, Validators.required],
  });

  constructor() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadAttachment(id);
    }
  }

  loadAttachment(id: string) {
    this.isLoading.set(true);
    this.service.getAll().subscribe({
      next: attachments => {
        const attachment = attachments.find(a => a.id === id);
        if (attachment) {
          this.attachment.set(attachment);
          this.form.patchValue(attachment);
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
    if (this.form.invalid || !this.attachment()) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting.set(true);
    this.error.set(null);

    const formValue = this.form.getRawValue();
    const id = this.attachment()!.id;

    this.service.update(Number(id), formValue).subscribe({
      next: () => {
        this.success.set(true);
        setTimeout(() => this.success.set(false), 3000);
      },
      error: err => this.error.set(err.message ?? 'Erreur'),
      complete: () => this.isSubmitting.set(false),
    });
  }
}
