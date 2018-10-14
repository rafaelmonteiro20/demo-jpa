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
import com.demo.model.Customer;
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
	
	@Test
	public void testAssociationOverrides() {
		
		List<Customer> custumers = manager.createQuery(
				"select c from Customer c " +
				"where c.contactInfo.address.state = 'IL'", Customer.class)
				.getResultList();
		
		assertEquals(1, custumers.size());
		assertEquals("RAFAEL", custumers.get(0).getName());
		assertEquals("888 8888888", custumers.get(0).getContactInfo().getPhone());
	}

}
