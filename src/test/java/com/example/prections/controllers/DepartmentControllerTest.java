package com.example.prections.controllers;

import com.example.prections.entity.Department;
import com.example.prections.service.DepartmentService;
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

public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllDepartments() {
        Department department1 = new Department();
        department1.setId(1L);
        department1.setName("IT");
        Department department2 = new Department();
        department2.setId(2L);
        department2.setName("HR");
        List<Department> departments = Arrays.asList(department1, department2);
        when(departmentService.findAll()).thenReturn(departments);

        ResponseEntity<List<Department>> response = departmentController.getAllDepartments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

}