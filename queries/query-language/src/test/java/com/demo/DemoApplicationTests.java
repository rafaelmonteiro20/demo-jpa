package com.demo;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dto.EmployeeDetails;
import com.demo.model.Department;
import com.demo.model.Employee;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void testSelect() {
		List<Employee> employees = manager.createQuery(
				"select e from Employee e", Employee.class)
				.getResultList();
		
		assertEquals(10, employees.size());
	}

	@Test
	public void testDifferentEntityAsResult() {
		List<Department> departments = manager.createQuery(
				"select distinct e.department from Employee e", Department.class)
				.getResultList();
		
		assertEquals(5, departments.size());
	}
	
	@Test
	public void testProjection() {
		List<Object[]> employees = manager.createQuery(
				"select e.name, e.salary from Employee e " +
				"order by e.id", Object[].class)
				.getResultList();
		
		assertEquals(10, employees.size());
		assertEquals("SMITH", employees.get(0)[0]);
		assertEquals(800.0, (double) employees.get(0)[1], 0.0001);
	}
	
	@Test
	public void testConstructorExpression() {
		List<EmployeeDetails> employees = manager.createQuery(
				"select " +
				"	new com.demo.dto.EmployeeDetails(" +
				"   e.name, e.salary, e.department.name) " +
				"from Employee e", EmployeeDetails.class)
				.getResultList();
		
		assertEquals(10, employees.size());
	}
	
}
