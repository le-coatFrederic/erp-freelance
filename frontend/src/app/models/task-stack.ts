import { TaskStackTransitionResponse } from './task-stack-transition';

export interface TaskStackRequest {
  name: string;
  description: string;
}

export interface TaskStackResponse {
  id: number;
  name: string;
  description: string;
  outgoing_transitions?: TaskStackTransitionResponse[];
}
