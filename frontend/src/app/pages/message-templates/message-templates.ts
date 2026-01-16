import { Component } from '@angular/core';
import { MessageTemplatesList } from '../../components/message-templates-list/message-templates-list';
import { MessageTemplatesForm } from '../../components/message-templates-form/message-templates-form';

@Component({
  selector: 'app-message-templates',
  imports: [MessageTemplatesList, MessageTemplatesForm],
  templateUrl: './message-templates.html',
  styleUrl: './message-templates.scss',
})
export class MessageTemplates {}
