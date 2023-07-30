package com.leonjr.springenterprisebackend.models.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.leonjr.springenterprisebackend.dto.CompanyDto;
import com.leonjr.springenterprisebackend.models.Company;

@Service
public class CompanyMapper implements Function<Company, CompanyDto> {

    @Override
    public CompanyDto apply(Company obj) {
        var employees = obj.getEmployees();
        return new CompanyDto(
                obj.getUuid(),
                obj.getName(),
                obj.getCnpj(),
                obj.getBalance(),
                obj.getEmails(),
                employees != null ? employees.stream().map(new EmployeeMapper()::apply).toList() : null);
    }
}