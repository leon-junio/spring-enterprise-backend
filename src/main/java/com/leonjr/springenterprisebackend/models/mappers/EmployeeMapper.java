package com.leonjr.springenterprisebackend.models.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.leonjr.springenterprisebackend.dto.EmployeeDto;
import com.leonjr.springenterprisebackend.models.Employee;

@Service
public class EmployeeMapper implements Function<Employee,EmployeeDto>{

    private final AddressMapper addressMapper = new AddressMapper();

    @Override
    public EmployeeDto apply(Employee arg0) {
        return new EmployeeDto(
            arg0.getUuid(),
            arg0.getName(),
            arg0.getCpf(),
            arg0.getPhones(),
            arg0.getEmails(),
            addressMapper.apply(arg0.getAddress()),
            arg0.getContrationDate(),
            arg0.getBirthDate(),
            arg0.getSalary(),
            arg0.getEmployeerole()
        );
    }
    
}
