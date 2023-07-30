package com.leonjr.springenterprisebackend.models;

import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;
    @Column(nullable = false)
    @NotBlank
    private String street;
    @Column(nullable = false)
    @NotBlank
    private String number;
    private String complement;
    @Column(nullable = false)
    @NotBlank
    private String neighborhood;
    @Column(nullable = false)
    @NotBlank
    private String city;
    @Column(nullable = false, length = 2, columnDefinition = "char(2)")
    @Length(min = 2, max = 2)
    @NotBlank
    private String state;
    @Column(nullable = false, length = 8, columnDefinition = "char(8)")
    @Length(min = 8, max = 8)
    @NotBlank
    private String zipCode;
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private Set<Employee> employee;

    @PrePersist
    public void prePersist() {
        setUuid(java.util.UUID.randomUUID().toString());
    }
}