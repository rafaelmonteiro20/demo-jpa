package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity 
@Table(name = "attachment")
public class Attachment extends BaseAttachment {
 
    @Lob
    private byte[] content;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
    
}
