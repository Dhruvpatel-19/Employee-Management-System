package com.example.empManagement;

import com.example.empManagement.entity.Employee;

import com.example.empManagement.service.EmployeeService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;


    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    List<Employee> list = Arrays.asList( new Employee(1 , "AAA" , "BBB" , "aaa@gmail.com" , "pass" , "NORMAL")   ,  new Employee(2 , "CCC" , "DDD" , "bbb@gmail.com" , "pass" , "ADMIN"));

    @Test
    public void getEmployeeTest() throws Exception {
        //when( employeeRepository.findAll()).thenReturn(list);

        //assertEquals( 2 , employeeService.getAllEmployee().size());

        Mockito.when( employeeService.getAllEmployee()).thenReturn(list);

        mockMvc.perform( MockMvcRequestBuilders.get(  "/getAllEmp").contentType( MediaType.APPLICATION_JSON)).andExpect(  content().string( objectMapper.writeValueAsString(list)  ) );

    }

    @Test
    public void addEmployeeByIdTest() throws Exception {

        Employee employee = new Employee(1 , "AAA" , "BBB" , "aaa@gmail.com" , "pass" , "NORMAL");

        Mockito.when( employeeService.addEmployee(employee)).thenReturn(employee);

        String content = objectWriter.writeValueAsString(employee);

        mockMvc.perform( MockMvcRequestBuilders.post("/addEmp").content(content).contentType(MediaType.APPLICATION_JSON)).andExpect( content().string(content));

    }

    @Test
    public void getEmployeeByIdTest() throws Exception {

        Employee employee = new Employee(1 , "AAA" , "BBB" , "aaa@gmail.com" , "pass" , "NORMAL");

        Mockito.when( employeeService.getEmployee(employee.getId())).thenReturn(employee);

        mockMvc.perform( MockMvcRequestBuilders.get("/getEmp/1").contentType(MediaType.APPLICATION_JSON)).andExpect(content().string( objectMapper.writeValueAsString(employee)));
    }


    @Test
    public void updateEmployeeTest() throws Exception {
        Employee employee = new Employee(1 , "AAA" , "BBB" , "aaa@gmail.com" , "pass" , "NORMAL");

        Mockito.when( employeeService.updateEmployee(employee.getId(),employee)).thenReturn(employee);

        String content = objectWriter.writeValueAsString(employee);
        mockMvc.perform( MockMvcRequestBuilders.put( "/updateEmp/1").content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(content().string(content));

    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        Employee employee = new Employee(1 , "AAA" , "BBB" , "aaa@gmail.com" , "pass" , "NORMAL");

        Mockito.when( employeeService.delEmployee(employee.getId())).thenReturn("Employee data deleted");

        mockMvc.perform( MockMvcRequestBuilders.delete("/delEmp/1").contentType(MediaType.APPLICATION_JSON)).andExpect(content().string("Employee data deleted"));

    }
}
