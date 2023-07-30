package com.leonjr.springenterprisebackend.models.utils;

import java.util.Date;
import java.util.List;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.leonjr.springenterprisebackend.models.Address;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class Person {
    @Column(nullable = false, length = 255)
    protected String name;
    @Column(nullable = false, length = 11)
    protected String cpf;
    @Column(nullable = false)
    protected Date birthDate;
    protected List<String> emails;
    protected List<String> phones;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Address address;
}
