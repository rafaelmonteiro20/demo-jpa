package com.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Announcement extends Topic {
 
    @Temporal(TemporalType.TIMESTAMP)
    private Date validUntil;
 
    public Date getValidUntil() {
		return validUntil;
	}
    
    public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

}
