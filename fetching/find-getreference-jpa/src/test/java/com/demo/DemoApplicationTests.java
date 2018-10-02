package com.demo;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Post;
import com.demo.model.PostComment;

/**
 * https://vladmihalcea.com/
 */

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void testSimpleFind() {
		Post post = manager.find(Post.class, 1L);
		assertEquals("High Performance Java Persistence", post.getTitle());
	}
	
	@Test
	public void testFind() {
		Post post = manager.find(Post.class, 1L);
		
		PostComment comment = new PostComment();
		comment.setReview("Just Awesome!");
		comment.setPost(post);
		
		manager.persist(comment);
	}
	
	@Test
	public void testGetReference() {
		
		Post post = manager.getReference(Post.class, 1L);
		
		PostComment comment = new PostComment();
		comment.setReview("Just Awesome 2!");
		comment.setPost(post);
		
		manager.persist(comment);
	}

}
