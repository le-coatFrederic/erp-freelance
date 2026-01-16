export enum HistoryChangeType {
  CREATE = 'CREATE',
  UPDATE = 'UPDATE',
  DELETE = 'DELETE',
}

export const HistoryChangeTypeMapper: Record<HistoryChangeType, string> = {
  [HistoryChangeType.CREATE]: 'Création',
  [HistoryChangeType.UPDATE]: 'Modification',
  [HistoryChangeType.DELETE]: 'Suppression',
};

export const HistoryChangeTypeList = Object.values(HistoryChangeType);
