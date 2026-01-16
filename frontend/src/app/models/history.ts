import { HistoryChangeType } from './values/history-change-type';
import { MessageTemplateResponse } from './message-template';

export interface HistoryResponse {
  id: number;
  message_template: MessageTemplateResponse;
  change_type: HistoryChangeType;
  created_on: string;
}
