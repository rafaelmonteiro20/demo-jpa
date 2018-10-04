package com.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Post;
import com.demo.model.PostDTO;

/**
 * https://vladmihalcea.com
 */

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void testWithJPATuple() {
		
		Post post = new Post();
		post.setCreateBy("Rafael Monteiro");
		post.setTitle("High-Performance Java Persistence");
		post.setUpdateBy("Rafael Monteiro");
		manager.persist(post);
		
		List<Tuple> postDTOs = manager.createQuery(
			"select p.id as id, p.title as title from Post p", 
			Tuple.class).getResultList();
				 
		assertFalse(postDTOs.isEmpty());
				 
		Tuple postDTO = postDTOs.get(0);
		assertEquals("High-Performance Java Persistence", postDTO.get("title"));
	}
	
	@Test
	public void testWithConstructorExpression() {
		
		Post post = new Post();
		post.setCreateBy("Rafael Monteiro");
		post.setTitle("High-Performance Java Persistence");
		post.setUpdateBy("Rafael Monteiro");
		manager.persist(post);
		
		List<PostDTO> postDTOs = manager.createQuery(
			"select new com.demo.model.PostDTO(" +
			"	p.id, p.title) from Post p ",
			PostDTO.class).getResultList();
		
		PostDTO postDTO = postDTOs.get(0);
		assertEquals("High-Performance Java Persistence", postDTO.getTitle());
	}
	
	@Test
	public void testWithTupleAndNativeQuerie() {
		
		Post post = new Post();
		post.setCreateBy("Rafael Monteiro");
		post.setTitle("High-Performance JPA");
		post.setUpdateBy("Rafael Monteiro");
		manager.persist(post);
		
		@SuppressWarnings("unchecked")
		List<Tuple> postDTOs = manager.createNativeQuery(
			"SELECT " +
			"	p.id AS id, " +
			"   p.title AS title " +
			"FROM Post p ",
			Tuple.class).getResultList();
				 
		Tuple postDTO = postDTOs.get(0);
		assertEquals("High-Performance JPA", postDTO.get("title"));
	}
	
	@Test
	public void testWithConstructorResult() {
		
		Post post = new Post();
		post.setCreateBy("Rafael Monteiro");
		post.setTitle("High-Performance JPA");
		post.setUpdateBy("Rafael Monteiro");
		manager.persist(post);
		
		List<PostDTO> postDTOs = manager.createNamedQuery("PostDTO", 
				PostDTO.class).getResultList();
				 
		PostDTO postDTO = postDTOs.get(0);
		assertEquals("High-Performance JPA", postDTO.getTitle());
	}
	
}
