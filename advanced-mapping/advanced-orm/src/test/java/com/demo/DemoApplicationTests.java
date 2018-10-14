package com.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.model.ContactInfo;
import com.demo.model.Employee;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void testConverter() {
	
		List<Employee> employees = manager.createQuery(
			"select e from Employee e where e.active = true", Employee.class)
			.getResultList();
		
		assertEquals(4, employees.size());
	}
	
	@Test
	public void testEmbeddable() {
		
		List<ContactInfo> employees = manager.createQuery(
				"select e.contactInfo from Employee e " +
				"where e.contactInfo.address.state = 'NY'", ContactInfo.class)
				.getResultList();
		
		assertEquals(2, employees.size());
	}

}
