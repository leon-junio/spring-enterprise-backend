package com.leonjr.springenterprisebackend.controllers;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonjr.springenterprisebackend.dto.CompanyDto;
import com.leonjr.springenterprisebackend.exceptions.ServiceException;
import com.leonjr.springenterprisebackend.requests.CompanyRequest;
import com.leonjr.springenterprisebackend.services.CompanyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/company")
@RequiredArgsConstructor
public class CompanyController extends BaseController {
    private final CompanyService companyService;

    @GetMapping("/{uuid}")
    public ResponseEntity<CompanyDto> getCompanyData(
            @PathVariable(value = "uuid") String uuid) throws ServiceException, NotFoundException {
        return ResponseEntity.ok(companyService.getCompany(uuid));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies(@RequestParam(required = false) Integer per_page,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer page) throws ServiceException, NotFoundException {
        return ResponseEntity.ok(companyService.getAllCompanies(paginate(page, per_page, sort)));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody @Valid CompanyRequest request)
            throws ServiceException, NotFoundException {
        return ResponseEntity.ok(companyService.createCompany(request));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody @Valid CompanyRequest request,
            @PathVariable(value = "uuid") String uuid) throws ServiceException, NotFoundException {
        return ResponseEntity.ok(companyService.updateCompany(request, uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCompany(@PathVariable(value = "uuid") String uuid)
            throws ServiceException, NotFoundException {
        companyService.deleteCompany(uuid);
        return ResponseEntity.noContent().build();
    }
}