package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post_comment")
public class PostComment implements Identifiable<Long> {

	@Id
	private Long id;
	
	private String review;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	/**
	 * hibernate only
	 */
	public PostComment() {

	}
	
	public PostComment(Long id, String review) {
		this.id = id;
		this.review = review;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getReview() {
		return review;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}
