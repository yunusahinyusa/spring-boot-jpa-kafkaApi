package com.ysahin.spring.jpa.kafkaApi.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String EmployeeName;
    private String EmployeeEmail;
}