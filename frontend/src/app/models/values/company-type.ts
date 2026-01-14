export enum CompanyType {
    ESN = "ESN",
    FINAL_CUSTOMER = "FINAL_CUSTOMER",
}

export const CompanyTypeMapper: Record<CompanyType, string> = {
  [CompanyType.ESN]: 'ESN',
  [CompanyType.FINAL_CUSTOMER]: 'Client final',
};

export const CompanyTypeList = Object.values(CompanyType);