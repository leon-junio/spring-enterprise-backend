package com.leonjr.springenterprisebackend.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.leonjr.springenterprisebackend.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByCpf(String cpf);

    List<Employee> findByCompanyId(Long id, Pageable pageable);

    Optional<Employee> findByUuid(String uuid);
}
