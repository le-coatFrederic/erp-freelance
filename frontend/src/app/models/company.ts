import { CompanyType } from "./values/company-type";

export interface Company {
    id?: number,
    name: string,
    siret: string,
    category: CompanyType
    size: number
};

export interface CompanyLight {
    id: number,
    name: string,
}