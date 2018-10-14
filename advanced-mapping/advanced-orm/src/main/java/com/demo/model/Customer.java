package com.demo.model;

import javax.persistence.AssociationOverride;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	private Long id;
	
	private String name;
	
	@Embedded
	@AssociationOverride(name = "address", joinColumns = @JoinColumn(name = "addr_id"))
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

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
	
}
