export enum MessageTemplateType {
    FIRST_CONTACT = "FIRST_CONTACT",
}

export const MessageTemplateTypeMapper: Record<MessageTemplateType, string> = {
    [MessageTemplateType.FIRST_CONTACT]: "Premier contact",
}

export const MessageTemplateTypeList = Object.values(MessageTemplateType);