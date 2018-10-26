package com.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post implements Identifiable<Long> {

	@Id
	private Long id;
	
	private String title;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostComment> comments = new ArrayList<>();

	/**
	 * hibernate only
	 */
	public Post() {

	}
	
	public Post(Long id, String title) {
		this.id = id;
		this.title = title;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<PostComment> getComments() {
		return comments;
	}

	public void addComment(PostComment comment) {
		this.comments.add(comment);
		comment.setPost(this);
	}
	
	@Override
	public String toString() {
		return id + " - " + title;
	}
	
}
