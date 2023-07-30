package com.leonjr.springenterprisebackend.dto;

import java.util.List;

import lombok.NonNull;

public record CompanyDto(
        @NonNull String id,
        @NonNull String name,
        @NonNull String cnpj,
        float balance,
        List<String> emails,
        List<EmployeeDto> employees
) {}