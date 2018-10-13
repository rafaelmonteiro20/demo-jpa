package com.demo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void test() {
	
	}

}
