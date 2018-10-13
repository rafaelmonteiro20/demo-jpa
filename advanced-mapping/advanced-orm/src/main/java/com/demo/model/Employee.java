package com.demo.model;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.demo.converter.BooleanToIntegerConverter;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	private Long id;
	
	private String name;
	
	@Convert(converter = BooleanToIntegerConverter.class)
	private boolean active;

	@Embedded
	private ContactInfo contactInfo = new ContactInfo();

	
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
	
}
