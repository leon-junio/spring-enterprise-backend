package com.leonjr.springenterprisebackend.requests;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
    @NotBlank
    @Length(min = 3, max = 255)
    private String name;
    @NotBlank
    @Length(min = 14, max = 14)
    private String cnpj;
    private float balance;
    @NotEmpty
    private List<String> emails;
}
