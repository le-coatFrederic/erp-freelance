import { AttachmentType } from "./values/attachment-type";

export interface AttachmentRequest {
    id?: number,
    name: string,
    description: string,
    type: AttachmentType
}

export interface AttachmentResponse {
    id: string,
    name: string,
    description: string,
    type: AttachmentType
}