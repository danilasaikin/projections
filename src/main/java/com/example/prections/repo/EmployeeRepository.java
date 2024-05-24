package com.example.prections.repo;

import com.example.prections.EmployeeProjection;
import com.example.prections.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e")
    List<EmployeeProjection> findAllWithProjection();
}
