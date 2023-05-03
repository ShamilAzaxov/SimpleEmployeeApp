package com.springboot.backend.service.impl;

import com.springboot.backend.exception.ResourceNotFoundException;
import com.springboot.backend.model.Employee;
import com.springboot.backend.repository.EmployeeRepository;
import com.springboot.backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id){

//        Optional<Employee> employee = repository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }
//        else {
//            throw new ResourceNotFoundException("Employee", "id", id);
//        }

        return  repository.findAllById(Collections.singleton(id)).stream().findFirst().
                orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id){

        Employee existingEmployee = repository.findById(id).
                orElseThrow(()->  new ResourceNotFoundException("Employee", "id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        repository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id){

        repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "id", id));

        repository.deleteById(id);
    }
}
