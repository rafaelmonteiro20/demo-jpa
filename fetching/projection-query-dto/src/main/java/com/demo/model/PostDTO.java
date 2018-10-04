package com.demo.model;

public class PostDTO {

	private Long id;
	
	private String title;
	
	public PostDTO(Long id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
}
