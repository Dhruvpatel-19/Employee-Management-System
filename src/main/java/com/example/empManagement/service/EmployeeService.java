package com.example.empManagement.service;

import com.example.empManagement.entity.Employee;
import com.example.empManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).orElse(null);
    }


    public String delEmployee(int id) {
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return "Employee data deleted";
        }
        else{
            return "Employee doesn't exist";
        }
    }

    public Employee updateEmployee(int id, Employee employee) {
        if(employeeRepository.existsById(id)){

            Employee e = employeeRepository.findById(id).orElse(null);
            e.setFirstName(employee.getFirstName());
            e.setLastName(employee.getLastName());
            e.setEmailId(employee.getEmailId());
            return employeeRepository.save(e);

        }
        else{
            return null;
        }
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
