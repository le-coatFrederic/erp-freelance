export enum TaskCategory {
  CALL = 'CALL',
  MESSAGE = 'MESSAGE',
  MEETING = 'MEETING',
  FOLLOW_UP = 'FOLLOW_UP',
  ACQUISITION = 'ACQUISITION',
  OTHER = 'OTHER',
}

export const TaskCategoryMapper: Record<TaskCategory, string> = {
  [TaskCategory.CALL]: 'Appel',
  [TaskCategory.MESSAGE]: 'Message',
  [TaskCategory.MEETING]: 'Réunion',
  [TaskCategory.FOLLOW_UP]: 'Suivi',
  [TaskCategory.ACQUISITION]: 'Acquisition',
  [TaskCategory.OTHER]: 'Autre',
};

export const TaskCategoryList = Object.values(TaskCategory);
