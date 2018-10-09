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
import com.demo.model.PostDetails;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void test() {
	
		Post post = manager.getReference(Post.class, 1L);
		
		PostDetails details = new PostDetails("Rafael Monteiro");
		details.setPost(post);
		
		manager.persist(details);
		
		PostDetails postDetails = manager.find(PostDetails.class, post.getId());
		assertEquals("Rafael Monteiro", postDetails.getCreatedBy());
	}

}
