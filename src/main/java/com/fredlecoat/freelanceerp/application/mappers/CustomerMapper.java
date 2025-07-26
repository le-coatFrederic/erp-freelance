package com.fredlecoat.freelanceerp.application.mappers;

import com.fredlecoat.freelanceerp.application.dtos.CustomerCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.CustomerShowAllDTO;
import com.fredlecoat.freelanceerp.application.dtos.CustomerUpdateDTO;
import com.fredlecoat.freelanceerp.domain.entities.Customer;
import com.fredlecoat.freelanceerp.domain.services.CustomerService;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private final CustomerService customerService;

    public CustomerMapper(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer toEntity(CustomerCreateDTO customerDTO) {
        return new Customer(
                customerDTO.name(),
                customerDTO.socialReason(),
                customerDTO.SIRET(),
                customerDTO.address(),
                customerDTO.mail(),
                customerDTO.phone(),
                customerDTO.website(),
                customerDTO.type(),
                customerDTO.status(),
                customerDTO.gap()
        );
    }

    public Customer toEntity(CustomerUpdateDTO customerDTO) {
        Customer customer = this.customerService.findById(customerDTO.id());

        customer.setName(customerDTO.name() != null ? customerDTO.name() : customer.getName());
        customer.setSocialReason(customerDTO.socialReason() != null ? customerDTO.socialReason() : customer.getSocialReason());
        customer.setSIRET(customerDTO.SIRET() != null ? customerDTO.SIRET() : customer.getSIRET());
        customer.setAddress(customerDTO.address() != null ? customerDTO.address() : customer.getAddress());
        customer.setMail(customerDTO.mail() != null ? customerDTO.mail() : customer.getMail());
        customer.setPhone(customerDTO.phone() != null ? customerDTO.phone() : customer.getPhone());
        customer.setWebsite(customerDTO.website() != null ? customerDTO.website() : customer.getWebsite());
        customer.setType(customerDTO.type() != null ? customerDTO.type() : customer.getType());
        customer.setStatus(customerDTO.status() != null ? customerDTO.status() : customer.getStatus());
        customer.setContactSituation(customerDTO.gap() != null ? customerDTO.gap() : customer.getContactSituation());

        return customer;
    }

    public CustomerShowAllDTO toDTO(Customer customer) {
        return new CustomerShowAllDTO(
                customer.getId(),
                customer.getName(),
                customer.getSocialReason(),
                customer.getSIRET(),
                customer.getAddress(),
                customer.getMail(),
                customer.getPhone(),
                customer.getWebsite(),
                customer.getType(),
                customer.getStatus(),
                customer.getContactSituation()
        );
    }
}
