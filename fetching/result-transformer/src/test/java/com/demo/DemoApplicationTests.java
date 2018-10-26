package com.demo;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.model.Post;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void contextLoads() {
		
		List<Post> posts = manager.createQuery(
				"select p " +
			    "from Post p " +
			    "left join fetch p.comments " +
			    "order by p.id", Post.class)
			.setMaxResults(10)
			.getResultList();
		
		posts.forEach(System.out::println);
	}

}
