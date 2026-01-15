import { MessageTemplateType } from "./values/message-template-type";

export interface MessageTemplateRequest {
    id?: number,
    subject: string,
    description: string,
    type: MessageTemplateType,
    content: string,
}

export interface MessageTemplateResponse {
    id: number,
    subject: string,
    description: string,
    type: MessageTemplateType,
    content: string,
}
