package com.demo.model;

import javax.persistence.Entity;

@Entity
public class Post extends Topic {
 
    private String content;
 
    public String getContent() {
		return content;
	}
    
    public void setContent(String content) {
		this.content = content;
	}
    
}