package com.demo.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.demo.model.validator.Even;
import com.demo.model.validator.group.FullTime;
import com.demo.model.validator.group.PartTime;

public class Employee implements Identifiable<Long> {

	@NotNull
	private Long id;

	@NotNull
	@Size(max = 30)
	private String name;

	@Past
	private LocalDate birthDate;

	@NotNull
	private TypeEmployee type;
	
	@NotNull(groups = FullTime.class)
	@Null(groups = PartTime.class)
	private Long salary;
	
	@NotNull(groups = PartTime.class)
	@Null(groups = FullTime.class)
	private Double hourlyWage;
	
	@Even
	private Integer parkingSpace;

	public Employee() {

	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public TypeEmployee getType() {
		return type;
	}
	
	public void setType(TypeEmployee type) {
		this.type = type;
	}
	
	public Long getSalary() {
		return salary;
	}
	
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	
	public Double getHourlyWage() {
		return hourlyWage;
	}
	
	public void setHourlyWage(Double hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
	
	public Integer getParkingSpace() {
		return parkingSpace;
	}
	
	public void setParkingSpace(Integer parkingSpace) {
		this.parkingSpace = parkingSpace;
	}
	
}
