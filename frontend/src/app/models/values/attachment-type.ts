export enum AttachmentType {
    PDF = "PDF",
    TEXT = "TEXT",
    IMAGE = "IMAGE",
}

export const AttachmentTypeMapper: Record<AttachmentType, string> = {
    [AttachmentType.PDF]: "Pdf",
    [AttachmentType.TEXT]: "Texte",
    [AttachmentType.IMAGE]: "Image",
}

export const AttachmentTypeList = Object.values(AttachmentType);