import { TransitionType } from './values/transition-type';
import { TaskStackResponse } from './task-stack';

export interface TaskStackTransitionRequest {
  source_stack_id: number;
  destination_stack_id: number;
  transition_type: TransitionType;
  auto_transition_threshold_hours?: number;
}

export interface TaskStackTransitionResponse {
  id: number;
  source_stack: TaskStackResponse;
  destination_stack: TaskStackResponse;
  transition_type: TransitionType;
  auto_transition_threshold_hours?: number;
}
