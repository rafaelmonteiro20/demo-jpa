package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "topic_statistics")
public class TopicStatistics {
 
    @Id @GeneratedValue
    private Long id;
 
    @MapsId
    @OneToOne
    private Topic topic;
 
    private long views;

    
    public TopicStatistics() {

    }
	public TopicStatistics(Topic topic) {
		this.topic = topic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public long getViews() {
		return views;
	}

	public void incrementViews() {
		this.views += 1;
	}

}