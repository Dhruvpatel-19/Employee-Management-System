package com.example.empManagement.controller;

import com.example.empManagement.entity.Employee;
import com.example.empManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/addEmp" , method = RequestMethod.POST)
    public Employee addEmployee(@RequestBody Employee employee){
        String encryptedPass = bCryptPasswordEncoder.encode(employee.getPassword());
        employee.setPassword(encryptedPass);
        return employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/getEmp/{id}" , method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id")int id){
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = "/getAllEmp" , method = RequestMethod.GET)
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @RequestMapping(value = "/delEmp/{id}" , method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") int id){
        return employeeService.delEmployee(id);
    }

    @RequestMapping(value = "/updateEmp/{id}" , method = RequestMethod.PUT)
    public Employee updateEmployee(@PathVariable("id")int id , @RequestBody Employee employee){
        return employeeService.updateEmployee(id , employee);
    }
}
