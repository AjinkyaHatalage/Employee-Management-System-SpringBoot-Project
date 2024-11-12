package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.EmployeeDto;
import com.example.emsbackend.entity.Employee;
import com.example.emsbackend.exception.ResourceNotFoundException;
import com.example.emsbackend.mapper.EmployeeMapper;
import com.example.emsbackend.repository.EmployeeRepository;
import com.example.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
       Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not existed with given ID :"+ employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee>employees = employeeRepository.findAll();

        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(long emplyeeID, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(emplyeeID).orElseThrow(
                ()-> new ResourceNotFoundException("Employee is not Exists with given ID"+ emplyeeID)
        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(long employeeID) {
        Employee employee = employeeRepository.findById(employeeID).orElseThrow(
                ()-> new ResourceNotFoundException("Employee is not Exists with given ID"+ employeeID)
        );
        employeeRepository.deleteById(employeeID);
    }
}
