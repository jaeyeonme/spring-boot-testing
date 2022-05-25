package io.jaeyeon.springboottesting.service;

import io.jaeyeon.springboottesting.exception.ResourceNotFoundException;
import io.jaeyeon.springboottesting.model.Employee;
import io.jaeyeon.springboottesting.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()) {
            throw new ResourceNotFoundException("Employee already exist with given email:" + employee.getEmail());
        }

        return employeeRepository.save(employee);
    }

    List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    Employee updateEmployee(Employee updatedEmployee) {
        return employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
