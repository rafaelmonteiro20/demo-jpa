package com.demo.listener;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.demo.model.Customer;

public class AgeValidator {
	
	private static final int MIN_AGE = 18;

	@PreUpdate
	@PrePersist
	public void validate(Customer customer) {
		
		Period period = Period.between(customer.getBirthDate(), LocalDate.now());
		if(period.getYears() < MIN_AGE) {
			throw new RuntimeException("Minimum age is " + MIN_AGE);
		}
		
	}
	
}
