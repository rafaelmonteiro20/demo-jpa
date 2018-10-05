package com.demo.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	private Integer id;
	
	private String name;
	
	private double salary;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;
	
	@ElementCollection
	@CollectionTable(
		name = "employee_phone", 
		joinColumns = @JoinColumn(name = "employee_id"))
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name = "type")
	@Column(name = "number")
	private Map<PhoneType, String> phones = new HashMap<>();

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Map<PhoneType, String> getPhones() {
		return phones;
	}
	
	public void addPhone(PhoneType type, String number) {
		this.phones.put(type, number);
	}
	
	public void removePhone(PhoneType type) {
		this.phones.remove(type);
	}
	
}
