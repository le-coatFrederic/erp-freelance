package com.fredlecoat.freelanceerp.domain.services;

import com.fredlecoat.freelanceerp.domain.entities.Contact;
import com.fredlecoat.freelanceerp.domain.entities.Message;
import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;

import java.util.List;

public interface MessageService {
    public Message create(Message message);
    public Message update(Message message);
    public void delete(Long id);
    public List<Message> findAll();
    public Message findById(Long id);
    public List<Message> findByMessageTemplate(MessageTemplate messageTemplate);
    public List<Message> findByContact(Contact contact);
}
