package com.demo.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private Validator validator;
	
	public Employee save(Employee employee) {

		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		
		/**
		 * persist employee with JPA
		 */
		if(violations.isEmpty()) {
			return employee;
		}
		
		throw new ConstraintViolationException(violations);
	}
	
}
