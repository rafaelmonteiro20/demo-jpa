package com.demo;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Customer;
import com.demo.model.TypePerson;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void testPosLoad() {
		
		Customer customer = manager.find(Customer.class, 1L);
		assertEquals("RAFAEL MONTEIRO", customer.getName());
		assertEquals("111.111.111-11", customer.getDocument());
	
	}
	
	@Test
	public void testPrePersist() {
		
		Customer customer = new Customer();
		customer.setId(40L);
		customer.setName("JOE");
		customer.setDocument("22.222.555/8888-00");
		customer.setType(TypePerson.LEGAL);
		customer.setBirthDate(LocalDate.of(2005, Month.MAY, 15));
		
		manager.persist(customer);
		
		String document = manager.createQuery(
				"select document from Customer where id = :id", String.class)
			.setParameter("id", 40L)
			.getSingleResult();
		
		assertEquals("22222555888800", document);
		
	}

}
