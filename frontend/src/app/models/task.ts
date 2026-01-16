import { TaskCategory } from './values/task-category';
import { MessageTemplateResponse } from './message-template';
import { ContactResponse } from './contact';
import { TaskStackResponse } from './task-stack';

export interface TaskRequest {
  message_template_id: number;
  contact_id: number;
  task_stack_id: number;
  name: string;
  description: string;
  category: TaskCategory;
}

export interface TaskResponse {
  id: number;
  name: string;
  description: string;
  category: TaskCategory;
  message_template: MessageTemplateResponse;
  contact: ContactResponse;
  task_stack: TaskStackResponse;
}

export interface TaskMoveRequest {
  destination_stack_id: number;
  transition_id: number;
}
