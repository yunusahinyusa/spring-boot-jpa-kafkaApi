package com.ysahin.spring.jpa.kafkaApi.repository;

import com.ysahin.spring.jpa.kafkaApi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> { }
