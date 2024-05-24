package com.example.prections.controllers;

import com.example.prections.EmployeeProjection;
import com.example.prections.entity.Employee;
import com.example.prections.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEmployeesWithProjection() {
        EmployeeProjection employeeProjection1 = new EmployeeProjectionImpl("Testic Testov", "Manager", "IT");
        EmployeeProjection employeeProjection2 = new EmployeeProjectionImpl("Ivan Ivanov", "Developer", "HR");
        List<EmployeeProjection> employeeProjections = Arrays.asList(employeeProjection1, employeeProjection2);
        when(employeeService.findAllWithProjection()).thenReturn(employeeProjections);

        ResponseEntity<List<EmployeeProjection>> response = employeeController.getAllEmployeesWithProjection();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    private static class EmployeeProjectionImpl implements EmployeeProjection {
        private String fullName;
        private String position;
        private String departmentName;

        public EmployeeProjectionImpl(String fullName, String position, String departmentName) {
            this.fullName = fullName;
            this.position = position;
            this.departmentName = departmentName;
        }

        @Override
        public String getFullName() {
            return fullName;
        }

        @Override
        public String getPosition() {
            return position;
        }

        @Override
        public String getDepartmentName() {
            return departmentName;
        }
    }

}