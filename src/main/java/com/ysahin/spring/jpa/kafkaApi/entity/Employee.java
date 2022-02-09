package com.ysahin.spring.jpa.kafkaApi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "employeeName",length = 200)
	private String employeeName;

	@Column(name = "EmployeeEmail", length = 200)
	private String EmployeeEmail;

}
