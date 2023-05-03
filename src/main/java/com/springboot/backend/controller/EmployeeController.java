package com.springboot.backend.controller;

import com.springboot.backend.model.Employee;
import com.springboot.backend.service.EmployeeService;
import com.sun.source.doctree.SeeTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(service.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return service.getAllEmployees();
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "employeeId")  long id){
        return new ResponseEntity<Employee>(service.getEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "employeeId") long id,
                                                   @RequestBody Employee employee){
        return new ResponseEntity<Employee>(service.updateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable(name = "employeeId") long id){
        service.deleteEmployee(id);

        return new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);
    }
}
