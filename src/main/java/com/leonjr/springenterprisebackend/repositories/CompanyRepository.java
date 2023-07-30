package com.leonjr.springenterprisebackend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leonjr.springenterprisebackend.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCnpj(String cnpj);

    Optional<Company> findByUuid(String uuid);

    // @Query("SELECT e FROM company e JOIN e.FOREIGN d WHERE d.name = :name")
    // Company findByEmployeeName(@Param("name") String name);
}