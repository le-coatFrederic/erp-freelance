package com.fredlecoat.freelanceerp.application.mappers;

import com.fredlecoat.freelanceerp.application.dtos.MessageTemplateCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.MessageTemplateShowAllDTO;
import com.fredlecoat.freelanceerp.application.dtos.MessageTemplateUpdateDTO;
import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;
import com.fredlecoat.freelanceerp.domain.repositories.MessageTemplateRepository;
import com.fredlecoat.freelanceerp.domain.services.MessageService;
import com.fredlecoat.freelanceerp.domain.services.MessageTemplateService;
import org.springframework.stereotype.Component;

@Component
public class MessageTemplateMapper {
    private final MessageTemplateService messageTemplateService;

    MessageTemplateMapper(MessageTemplateService messageTemplateService) {
        this.messageTemplateService = messageTemplateService;
    }

    public MessageTemplate toEntity(MessageTemplateCreateDTO messageTemplateDTO) {
        return new MessageTemplate(
                messageTemplateDTO.subject(),
                messageTemplateDTO.body()
        );
    }

    public MessageTemplate toEntity(MessageTemplateUpdateDTO messageTemplateDTO) {
        MessageTemplate messageTemplate = this.messageTemplateService.findById(messageTemplateDTO.id());

        if (messageTemplate == null) {
            throw new IllegalArgumentException("The message template with id " + messageTemplateDTO.id() + " does not exist.");
        }

        if (messageTemplateDTO.subject() != null) {
            messageTemplate.setSubject(messageTemplateDTO.subject());
        }

        if (messageTemplateDTO.body() != null) {
            messageTemplate.setBody(messageTemplateDTO.body());
        }

        return messageTemplate;
    }

    public MessageTemplateShowAllDTO toAllDTO(MessageTemplate messageTemplate) {
        return new MessageTemplateShowAllDTO(
                messageTemplate.getId(),
                messageTemplate.getSubject(),
                messageTemplate.getBody()
        );
    }
}
