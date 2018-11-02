package com.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Identifiable<Long> {

	@Id
	private Long id;

	private String name;

	private String document;

	@Enumerated(EnumType.STRING)
	private TypePerson type;

	private LocalDate birthDate;

	
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

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public TypePerson getType() {
		return type;
	}

	public void setType(TypePerson type) {
		this.type = type;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@PreUpdate
	@PrePersist 
	private void preSave() {
		this.document = TypePerson.removeFormat(this.document);
	}
	
	@PostLoad
	private void postLoad() {
		this.document = type.format(this.document);
	}
	
}
