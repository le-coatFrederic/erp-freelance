package com.fredlecoat.freelanceerp.application.mappers;

import com.fredlecoat.freelanceerp.application.dtos.ContactCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.ContactShowAllDTO;
import com.fredlecoat.freelanceerp.application.dtos.ContactUpdateDTO;
import com.fredlecoat.freelanceerp.application.dtos.CustomerShowAllDTO;
import com.fredlecoat.freelanceerp.domain.entities.Contact;
import com.fredlecoat.freelanceerp.domain.entities.Customer;
import com.fredlecoat.freelanceerp.domain.services.ContactService;
import com.fredlecoat.freelanceerp.domain.services.CustomerService;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    private final ContactService contactService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public ContactMapper(ContactService contactService, CustomerService customerService, CustomerMapper customerMapper) {
        this.contactService = contactService;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    public Contact toEntity(ContactCreateDTO contactDTO) {
        Customer customer = this.customerService.findById(contactDTO.customer().id());

        return new Contact(
                contactDTO.firstname(),
                contactDTO.lastname(),
                contactDTO.email(),
                contactDTO.phone(),
                contactDTO.linkedin(),
                contactDTO.jobTitle(),
                customer
        );
    }

    public Contact toEntity(ContactUpdateDTO contactDTO) {
        Contact contact = this.contactService.findById(contactDTO.id());

        contact.setFirstName(contactDTO.firstname() != null ? contactDTO.firstname() : contact.getFirstName());
        contact.setLastName(contactDTO.lastname() != null ? contactDTO.lastname() : contact.getLastName());
        contact.setEmail(contactDTO.email() != null ? contactDTO.email() : contact.getEmail());
        contact.setPhone(contactDTO.phone() != null ? contactDTO.phone() : contact.getPhone());
        contact.setLinkedin(contactDTO.linkedin() != null ? contactDTO.linkedin() : contact.getLinkedin());
        contact.setJobTitle(contactDTO.jobTitle() != null ? contactDTO.jobTitle() : contact.getJobTitle());

        return contact;
    }

    public ContactShowAllDTO toDTO(Contact contact) {
        CustomerShowAllDTO customer = this.customerMapper.toDTO(contact.getCustomer());

        return new ContactShowAllDTO(
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getLinkedin(),
                contact.getJobTitle(),
                customer
        );
    }
}
