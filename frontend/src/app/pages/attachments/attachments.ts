import { Component } from '@angular/core';
import { AttachmentsList } from '../../components/attachments-list/attachments-list';
import { AttachmentsForm } from '../../components/attachments-form/attachments-form';

@Component({
  selector: 'app-attachments',
  imports: [AttachmentsList, AttachmentsForm],
  templateUrl: './attachments.html',
  styleUrl: './attachments.scss',
})
export class Attachments {}
