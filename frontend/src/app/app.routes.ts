import { Routes } from '@angular/router';
import { Companies } from './pages/companies/companies';
import { CompanyDetail } from './pages/company-detail/company-detail';
import { Contacts } from './pages/contacts/contacts';
import { ContactDetail } from './pages/contact-detail/contact-detail';
import { Attachments } from './pages/attachments/attachments';
import { AttachmentDetail } from './pages/attachment-detail/attachment-detail';
import { MessageTemplates } from './pages/message-templates/message-templates';
import { MessageTemplateDetail } from './pages/message-template-detail/message-template-detail';
import { Tasks } from './pages/tasks/tasks';
import { TaskDetail } from './pages/task-detail/task-detail';
import { TaskStacks } from './pages/task-stacks/task-stacks';
import { TaskStackDetail } from './pages/task-stack-detail/task-stack-detail';
import { TaskStackTransitions } from './pages/task-stack-transitions/task-stack-transitions';
import { TaskStackTransitionDetail } from './pages/task-stack-transition-detail/task-stack-transition-detail';
import { History } from './pages/history/history';

export const routes: Routes = [
  { path: '', redirectTo: '/companies', pathMatch: 'full' },
  { path: 'companies', component: Companies },
  { path: 'companies/:id', component: CompanyDetail },
  { path: 'contacts', component: Contacts },
  { path: 'contacts/:id', component: ContactDetail },
  { path: 'attachments', component: Attachments },
  { path: 'attachments/:id', component: AttachmentDetail },
  { path: 'message-templates', component: MessageTemplates },
  { path: 'message-templates/:id', component: MessageTemplateDetail },
  { path: 'tasks', component: Tasks },
  { path: 'tasks/:id', component: TaskDetail },
  { path: 'task-stacks', component: TaskStacks },
  { path: 'task-stacks/:id', component: TaskStackDetail },
  { path: 'task-stack-transitions', component: TaskStackTransitions },
  { path: 'task-stack-transitions/:id', component: TaskStackTransitionDetail },
  { path: 'history', component: History },
];
