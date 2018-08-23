package com.demo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Announcement;
import com.demo.model.Board;
import com.demo.model.Post;
import com.demo.model.Topic;
import com.demo.model.TopicStatistics;

@Component
public class CommandLine implements CommandLineRunner {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {

		inserts();
		
		List<Topic> topics = manager.createQuery("select t from Topic t where t.board.id = :boardId", Topic.class)
			.setParameter("boardId", 1L)
			.getResultList();
		
		topics.forEach(t -> System.out.println(t.getTitle()));
		
		TopicStatistics statistics = manager.createQuery(
			    "select s from TopicStatistics s join fetch s.topic t where t.id = :topicId", TopicStatistics.class)
			.setParameter("topicId", 1L)
			.getSingleResult();
		
		System.out.println(statistics);
	}
	
	public void inserts() {
		
		Board board = new Board("JPA and Hibernate");
		manager.persist(board);
		
		Post post = new Post();
		post.setOwner("Rafael Monteiro");
		post.setTitle("Inheritance");
		post.setContent("Best practices");
		post.setBoard(board);
		 
		manager.persist(post);
		 
		Announcement announcement = new Announcement();
		announcement.setOwner("Rafael");
		announcement.setTitle("New Release");
		announcement.setValidUntil(Timestamp.valueOf(LocalDateTime.now().plusMonths(1)));
		announcement.setBoard(board);
		 
		manager.persist(announcement);
		 
		TopicStatistics postStatistics = new TopicStatistics(post);
		postStatistics.incrementViews();
		manager.persist(postStatistics);
		 
		TopicStatistics announcementStatistics =  new TopicStatistics(announcement);
		announcementStatistics.incrementViews();
		manager.persist(announcementStatistics);
	
	}

}