package com.leonjr.springenterprisebackend.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.leonjr.springenterprisebackend.dto.CompanyDto;
import com.leonjr.springenterprisebackend.exceptions.ServiceException;
import com.leonjr.springenterprisebackend.models.Company;
import com.leonjr.springenterprisebackend.models.mappers.CompanyMapper;
import com.leonjr.springenterprisebackend.repositories.CompanyRepository;
import com.leonjr.springenterprisebackend.requests.CompanyRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyDto createCompany(@NonNull CompanyRequest request) throws ServiceException {
        try {
            var company = Company.builder()
                    .name(request.getName())
                    .cnpj(request.getCnpj())
                    .balance(request.getBalance())
                    .emails(request.getEmails())
                    .build();
            company = companyRepository.save(company);
            return companyMapper.apply(company);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Company creation failed due to a service exception: " + e.getMessage());
        }
    }

    public CompanyDto updateCompany(@NonNull CompanyRequest request, @NonNull String uuid)
            throws NotFoundException, ServiceException {
        var company = companyRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException());
        try {
            company.setName(request.getName());
            company.setCnpj(request.getCnpj());
            company.setBalance(request.getBalance());
            company.setEmails(request.getEmails());
            company = companyRepository.save(company);
            return companyMapper.apply(company);
        } catch (Exception e) {
            throw new ServiceException("Company update failed due to a a service exception: " + e.getMessage());
        }
    }

    public void deleteCompany(@NonNull String uuid) throws NotFoundException, ServiceException {
        var company = companyRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException());
        try {
            companyRepository.delete(company);
        } catch (Exception e) {
            throw new ServiceException("Company delete failed due to a service exception: " + e.getMessage());
        }
    }

    public CompanyDto getCompany(@NonNull String uuid) throws NotFoundException, ServiceException {
        var company = companyRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException());
        try {
            return companyMapper.apply(company);
        } catch (Exception e) {
            throw new ServiceException("Company get failed due to a service exception: " + e.getMessage());
        }
    }

    public List<CompanyDto> getAllCompanies(@NonNull Pageable paginate) throws ServiceException {
        try {
            var companies = companyRepository.findAll(paginate);
            return companies.stream().map(companyMapper).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Company get all failed due to a service exception: " + e.getMessage());
        }
    }

}
