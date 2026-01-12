package com.fredlecoat.erp_freelance.application.controllers;

import com.fredlecoat.erp_freelance.domain.entities.CompanyEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.CompanyTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.CompanyWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.entities.mappers.CompanyMapper;
import com.fredlecoat.erp_freelance.domain.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;

    @GetMapping
    public ResponseEntity<List<CompanyTotalResponse>> getAllCompanies() {
        return ResponseEntity.ok(this.companyService.getAll()
                .stream()
                .map(this.companyMapper::toDto)
                .toList()
        );
    }

    @PostMapping
    public ResponseEntity<CompanyTotalResponse> createCompany(@RequestBody CompanyWithoutIdRequest request) {
        CompanyEntity entity = this.companyService.create(this.companyMapper.toEntity(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.companyMapper.toDto(entity));
    }
}
