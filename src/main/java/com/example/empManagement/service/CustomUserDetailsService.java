package com.example.empManagement.service;
/*
import com.example.empManagement.entity.CustomUSerDetails;
import com.example.empManagement.entity.Employee;
import com.example.empManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByFirstName(username);
        if(employee == null){
            throw new UsernameNotFoundException("No User found");
        }
        return new CustomUSerDetails(employee);
    }
}
*/