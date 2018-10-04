package com.demo.dto;

public class EmployeeDetails {

	private String nome;
	private double salary;
	private String department;
	
	public EmployeeDetails(String nome, double salary, String department) {
		this.nome = nome;
		this.salary = salary;
		this.department = department;
	}

	public String getNome() {
		return nome;
	}

	public double getSalary() {
		return salary;
	}

	public String getDepartment() {
		return department;
	}
	
}
