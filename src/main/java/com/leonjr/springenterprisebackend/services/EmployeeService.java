package com.leonjr.springenterprisebackend.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leonjr.springenterprisebackend.dto.EmployeeDto;
import com.leonjr.springenterprisebackend.exceptions.InvalidRequestException;
import com.leonjr.springenterprisebackend.exceptions.ServiceException;
import com.leonjr.springenterprisebackend.models.Employee;
import com.leonjr.springenterprisebackend.models.mappers.EmployeeMapper;
import com.leonjr.springenterprisebackend.models.utils.EmployeeRole;
import com.leonjr.springenterprisebackend.repositories.AddressRepository;
import com.leonjr.springenterprisebackend.repositories.CompanyRepository;
import com.leonjr.springenterprisebackend.repositories.EmployeeRepository;
import com.leonjr.springenterprisebackend.requests.EmployeeRequest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    public List<EmployeeDto> getAll(@NonNull Pageable paginate) throws ServiceException {
        try {
            var employees = employeeRepository.findAll(paginate);
            return employees.stream().map(employeeMapper).toList();
        } catch (Exception e) {
            throw new ServiceException("Employee search failed due to a service exception: " + e.getMessage());
        }
    }

    public EmployeeDto createEmployee(@NonNull EmployeeRequest request)
            throws ServiceException, InvalidRequestException {
        try {
            var address = addressRepository.save(request.getAddress());
            var employee = Employee.builder()
                    .name(request.getName())
                    .salary(request.getSalary())
                    .address(request.getAddress())
                    .cpf(request.getCpf())
                    .phones(request.getPhones())
                    .emails(request.getEmails())
                    .address(address)
                    .contrationDate(request.getContratation_date())
                    .birthDate(request.getBirth_date())
                    .salary(request.getSalary())
                    .employeerole(EmployeeRole.valueOf(request.getEmployee_role()))
                    .company(companyRepository.findByUuid(request.getCompany_id())
                            .orElseThrow(() -> new InvalidRequestException("Company not found.")))
                    .build();
            employee = employeeRepository.save(employee);
            return employeeMapper.apply(employee);
        } catch (Exception e) {
            throw new ServiceException("Employee creation failed due to a service exception: " + e.getMessage());
        }
    }

    public EmployeeDto updateEmployee(@NonNull EmployeeRequest request, @NonNull String uuid)
            throws ServiceException, InvalidRequestException {
                var employee = employeeRepository.findByUuid(uuid)
                    .orElseThrow(() -> new InvalidRequestException("Employee not found."));
        try {
            employee.setName(request.getName());
            employee.setSalary(request.getSalary());
            employee.setAddress(request.getAddress());
            employee.setCpf(request.getCpf());
            employee.setPhones(request.getPhones());
            employee.setEmails(request.getEmails());
            employee.setAddress(addressRepository.save(request.getAddress()));
            employee.setContrationDate(request.getContratation_date());
            employee.setBirthDate(request.getBirth_date());
            employee.setSalary(request.getSalary());
            employee.setEmployeerole(EmployeeRole.valueOf(request.getEmployee_role()));
            employee.setCompany(companyRepository.findByUuid(request.getCompany_id())
                    .orElseThrow(() -> new InvalidRequestException("Company not found.")));
            employee = employeeRepository.save(employee);
            return employeeMapper.apply(employee);
        } catch (Exception e) {
            throw new ServiceException("Employee update failed due to a a service exception: " + e.getMessage());
        }
    }

    public void deleteEmployee(@NonNull String uuid) throws ServiceException, InvalidRequestException {
        var employee = employeeRepository.findByUuid(uuid)
                    .orElseThrow(() -> new InvalidRequestException("Employee not found."));
        try { 
            employeeRepository.delete(employee);
        } catch (Exception e) {
            throw new ServiceException("Employee delete failed due to a service exception: " + e.getMessage());
        }
    }

    public EmployeeDto getEmployeeByUuid(@NonNull String uuid) throws ServiceException, InvalidRequestException {
        var employee = employeeRepository.findByUuid(uuid)
                    .orElseThrow(() -> new InvalidRequestException("Employee not found."));
        try {
            return employeeMapper.apply(employee);
        } catch (Exception e) {
            throw new ServiceException("Employee search failed due to a service exception: " + e.getMessage());
        }
    }

    public EmployeeDto getEmployeeByCpf(@NonNull String cpf) throws ServiceException, InvalidRequestException {
        var employee = employeeRepository.findByCpf(cpf)
                    .orElseThrow(() -> new InvalidRequestException("Employee not found."));
        try {
            return employeeMapper.apply(employee);
        } catch (Exception e) {
            throw new ServiceException("Employee search failed due to a service exception: " + e.getMessage());
        }
    }

    public List<EmployeeDto> getEmployeesByCompany(String uuid, Pageable paginate)
            throws ServiceException, InvalidRequestException {
        var company_id = companyRepository.findByUuid(uuid).get().getId();
        var employees = employeeRepository.findByCompanyId(company_id, paginate);
        try {
            return employees.stream().map(employeeMapper).toList();
        } catch (Exception e) {
            throw new ServiceException("Employee search failed due to a service exception: " + e.getMessage());
        }
    }

}
