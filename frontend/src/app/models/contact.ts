import { Company } from "./company"

export interface ContactRequest {
    firstname: string,
    lastname: string,
    job: string,
    email: string,
    phone: string,
    linkedin: string,
    company_id: number
}

export interface ContactResponse {
    id: number,
    firstname: string,
    lastname: string,
    job: string,
    email: string,
    phone: string,
    linkedin: string,
    company: Company
}