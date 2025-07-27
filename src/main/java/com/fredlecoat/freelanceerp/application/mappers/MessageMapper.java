package com.fredlecoat.freelanceerp.application.mappers;

import com.fredlecoat.freelanceerp.application.dtos.MessageCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.MessageShowAllDTO;
import com.fredlecoat.freelanceerp.application.dtos.MessageUpdateDTO;
import com.fredlecoat.freelanceerp.domain.entities.Contact;
import com.fredlecoat.freelanceerp.domain.entities.Message;
import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;
import com.fredlecoat.freelanceerp.domain.services.ContactService;
import com.fredlecoat.freelanceerp.domain.services.MessageService;
import com.fredlecoat.freelanceerp.domain.services.MessageTemplateService;
import org.springframework.stereotype.Component;

@Component
    public class MessageMapper {
        private final MessageService messageService;
    private final ContactService contactService;
    private final MessageTemplateService messageTemplateService;
    private final ContactMapper contactMapper;
    private final MessageTemplateMapper messageTemplateMapper;

    public MessageMapper(MessageService messageService, ContactMapper contactMapper, ContactService contactService, MessageTemplateService messageTemplateService, MessageTemplateMapper messageTemplateMapper) {
        this.messageService = messageService;
        this.contactService = contactService;
        this.contactMapper = contactMapper;
        this.messageTemplateService = messageTemplateService;
        this.messageTemplateMapper = messageTemplateMapper;
    }

    public Message toEntity(MessageCreateDTO messageDTO) {
        Contact contact = this.contactService.findById(messageDTO.contactId());
        MessageTemplate messageTemplate = this.messageTemplateService.findById(messageDTO.messageTemplateId());

        return new Message(
                messageDTO.status(),
                messageDTO.plannedDate(),
                messageDTO.sendDate(),
                contact,
                messageTemplate
        );
    }

    public MessageShowAllDTO toDTO(Message message) {
        return new MessageShowAllDTO(
                message.getId(),
                message.getStatus(),
                message.getPlannedDate(),
                message.getSendDate(),
                this.contactMapper.toDTO(message.getContact()),
                this.messageTemplateMapper.toAllDTO(message.getTemplate())
        );
    }
}
