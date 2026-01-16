import { Component, inject } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { RouterLink } from '@angular/router';
import { MessageTemplateService } from '../../services/message-template-service';

@Component({
  selector: 'app-message-templates-list',
  imports: [RouterLink],
  templateUrl: './message-templates-list.html',
  styleUrl: './message-templates-list.scss',
})
export class MessageTemplatesList {
  private service = inject(MessageTemplateService);
  templates = toSignal(this.service.getAll(), { initialValue: [] });
}
