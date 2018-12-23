package com.demo.dto;

public class EmployeeDetails {

	private String name;
	private double salary;
	private String department;
	
	public EmployeeDetails(String name, double salary) {
		this(name, salary, null);
	}
	
	public EmployeeDetails(String name, double salary, String department) {
		this.name = name;
		this.salary = salary;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public String getDepartment() {
		return department;
	}
	
}
