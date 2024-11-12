package com.example.emsbackend.service;

import com.example.emsbackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(long employeeId);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto updateEmployee(long emplyeeID,EmployeeDto updatedEmployee);

    void deleteEmployee(long employeeID);
}
