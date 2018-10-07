package com.demo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "QUALITY")
public class QualityProject extends Project {

	@Column(name = "qa_rating")
	private Integer qaRating;

	public Integer getQaRating() {
		return qaRating;
	}

	public void setQaRating(Integer qaRating) {
		this.qaRating = qaRating;
	}
	
}
