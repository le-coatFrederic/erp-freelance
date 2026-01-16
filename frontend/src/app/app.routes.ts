import { Routes } from '@angular/router';
import { Companies } from './pages/companies/companies';
import { CompanyDetail } from './pages/company-detail/company-detail';
import { Contacts } from './pages/contacts/contacts';
import { Attachments } from './pages/attachments/attachments';
import { MessageTemplates } from './pages/message-templates/message-templates';
import { Tasks } from './pages/tasks/tasks';
import { TaskStacks } from './pages/task-stacks/task-stacks';
import { TaskStackTransitions } from './pages/task-stack-transitions/task-stack-transitions';
import { History } from './pages/history/history';

export const routes: Routes = [
  { path: '', redirectTo: '/companies', pathMatch: 'full' },
  { path: 'companies', component: Companies },
  { path: 'companies/:id', component: CompanyDetail },
  { path: 'contacts', component: Contacts },
  { path: 'attachments', component: Attachments },
  { path: 'message-templates', component: MessageTemplates },
  { path: 'tasks', component: Tasks },
  { path: 'task-stacks', component: TaskStacks },
  { path: 'task-stack-transitions', component: TaskStackTransitions },
  { path: 'history', component: History },
];
