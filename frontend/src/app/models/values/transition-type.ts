export enum TransitionType {
  MANUAL = 'MANUAL',
  AUTOMATIC = 'AUTOMATIC',
}

export const TransitionTypeMapper: Record<TransitionType, string> = {
  [TransitionType.MANUAL]: 'Manuel',
  [TransitionType.AUTOMATIC]: 'Automatique',
};

export const TransitionTypeList = Object.values(TransitionType);
