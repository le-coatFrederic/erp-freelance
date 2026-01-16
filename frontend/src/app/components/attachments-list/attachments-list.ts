import { Component, inject } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { AttachmentService } from '../../services/attachment-service';

@Component({
  selector: 'app-attachments-list',
  imports: [RouterLink],
  templateUrl: './attachments-list.html',
  styleUrl: './attachments-list.scss',
})
export class AttachmentsList {
  private service = inject(AttachmentService);
  attachments = toSignal(this.service.getAll(), { initialValue: [] });
}
