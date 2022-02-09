package com.ysahin.spring.jpa.kafkaApi.service.impl;

import com.ysahin.spring.jpa.kafkaApi.dto.EmployeeDto;
import com.ysahin.spring.jpa.kafkaApi.entity.Employee;
import com.ysahin.spring.jpa.kafkaApi.repository.EmployeeRepository;
import com.ysahin.spring.jpa.kafkaApi.service.EmployeeService;
import com.ysahin.spring.jpa.kafkaApi.util.UtilService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    UtilService utilService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto,HttpServletRequest request) throws Exception {
        long time = System.currentTimeMillis();
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        EmployeeDto list = modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
        time = System.currentTimeMillis() - time;
        utilService.createLogFile(request,time);
        return list;
    }

    @Override
    public List<EmployeeDto> getEmployees(HttpServletRequest request) throws IOException {
        long time = System.currentTimeMillis();
        List<Employee> employeeDtos = employeeRepository.findAll();
        List<EmployeeDto> dtos = employeeDtos.stream().map(Employee -> modelMapper.map(Employee, EmployeeDto.class)).collect(Collectors.toList());
        time = System.currentTimeMillis() - time;
        utilService.createLogFile(request,time);
        return dtos;
    }

    @Override
    public EmployeeDto getEmployeeById(@PathVariable("id") Long id,HttpServletRequest request) throws Exception {
        long time = System.currentTimeMillis();
        Employee employee = employeeRepository.getOne(id);
        time = System.currentTimeMillis() - time;
        utilService.createLogFile(request,time);
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employee,HttpServletRequest request) throws Exception {
        Optional<Employee> resultEmployee = employeeRepository.findById(id);
        long time = System.currentTimeMillis();
        if (resultEmployee.isPresent()) {
            resultEmployee.get().setEmployeeName(employee.getEmployeeName());
            resultEmployee.get().setEmployeeEmail(employee.getEmployeeEmail());
            time = System.currentTimeMillis() - time;
            utilService.createLogFile(request,time);
            return modelMapper.map(employeeRepository.save(resultEmployee.get()), EmployeeDto.class);
        }
        time = System.currentTimeMillis() - time;
        utilService.createLogFile(request,time);
        return null;
    }

    @Override
    public Boolean deleteEmployee(Long id,HttpServletRequest request) throws IOException {
        long time = System.currentTimeMillis();
        employeeRepository.deleteById(id);
        time = System.currentTimeMillis() - time;
        utilService.createLogFile(request,time);
        return true;
    }

}
