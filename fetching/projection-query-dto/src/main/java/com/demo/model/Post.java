package com.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedNativeQuery(
	name = "PostDTO",
	query =
		"SELECT " +
	    "	p.id AS id, " +
	    "	p.title AS title " +
	    "FROM Post p ",
	resultSetMapping = "PostDTO"
)
@SqlResultSetMapping(
	name = "PostDTO",
	classes = @ConstructorResult(
		targetClass = PostDTO.class,
	    columns = {
	    	@ColumnResult(name = "id", type = Long.class),
	    	@ColumnResult(name = "title", type = String.class)
	    }
	)
)

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_on")
	private Date createOn = new Date();
	
	@Column(name = "create_by")
	private String createBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_on")
	private Date updateOn = new Date();
	
	@Column(name = "update_by")
	private String updateBy;

	public Post() {

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
}
