package com.leonjr.springenterprisebackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leonjr.springenterprisebackend.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByZipCode(String zipCode);
    Optional<Address> findByUuid(String uuid);
}