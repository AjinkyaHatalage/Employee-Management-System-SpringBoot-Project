package com.example.emsbackend.controller;

import com.example.emsbackend.dto.EmployeeDto;
import com.example.emsbackend.entity.Employee;
import com.example.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class Employeecontroller {
    private EmployeeService employeeService;

    // Build Add Employee Rest API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build Get Employee Rest API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long employeeID){
       EmployeeDto employeeDto = employeeService.getEmployeeById(employeeID);
       return ResponseEntity.ok(employeeDto);
    }

    //Build Get All Employee Rest API
    @GetMapping
    public ResponseEntity<List<EmployeeDto> > getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }

    //Build Update Employee Rest API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long employeeID,
                                                      @RequestBody EmployeeDto updatedEmployeedetail){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeID,updatedEmployeedetail);
        return  ResponseEntity.ok(employeeDto);
    }

    //Build Delete Employee Rest API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")  long employeeID){
        employeeService.deleteEmployee(employeeID);
        return  ResponseEntity.ok("Employee Deleted succesfully: ");
    }

}
