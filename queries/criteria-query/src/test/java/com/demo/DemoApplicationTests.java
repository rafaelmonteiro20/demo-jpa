package com.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dto.EmployeeDetails;
import com.demo.model.Department;
import com.demo.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@PersistenceContext
	private EntityManager manager;
	
	@Test
	public void testSelect() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(root);
		
		List<Employee> employees = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(10, employees.size());
	}
	
	@Test
	public void testDifferentEntityAsResult() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = cb.createQuery(Department.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.distinct(true);
		criteria.select(root.get("department"));
		
		List<Department> departments = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(5, departments.size());
	}

	@Test
	public void testPathExpression() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(root)
				.where(cb.equal(root.get("department").get("name"), "SUPPORT"));
		
		List<Employee> employees = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(3, employees.size());
	}
	
	@Test
	public void testSelectingSingleExpressions() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteria = cb.createQuery(String.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(root.<String>get("name"))
				.where(cb.equal(root.get("department").get("name"), "ADMINISTRATIVE"));
		
		List<String> employees = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(2, employees.size());
		assertTrue(employees.contains("JAMES"));
		assertTrue(employees.contains("SOPHIA"));
	}
	
	@Test
	public void testProjection() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteria = cb.createQuery(Object[].class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(cb.array(root.get("name"), root.get("salary")));
		criteria.orderBy(cb.asc(root));
		
		List<Object[]> employees = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(10, employees.size());
		assertEquals("SMITH", employees.get(0)[0]);
		assertEquals(800.0, (double) employees.get(0)[1], 0.0001);
	}

	@Test
	public void testProjectionWithTuple() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(cb.tuple(root.get("name"), root.get("salary")));
		criteria.orderBy(cb.asc(root));
		
		List<Tuple> employees = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(10, employees.size());
		assertEquals("SMITH", employees.get(0).get(0));
		assertEquals(800.0, employees.get(0).get(1, Double.class), 0.0001);
	}
	
	@Test
	public void testSelectingMultipleExpressions() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.multiselect(
				root.get("name").alias("name"), 
				root.get("salary").alias("salary"));
		
		criteria.orderBy(cb.desc(root));
		
		List<Tuple> employees = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(10, employees.size());
		assertEquals("MARTIN", employees.get(0).get("name"));
		assertEquals(4000.0, employees.get(0).get("salary", Double.class), 0.0001);
	}
	
	@Test
	public void testConstructorExpression() {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<EmployeeDetails> criteria = cb.createQuery(EmployeeDetails.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(cb.construct(EmployeeDetails.class,
				root.get("name"), 
				root.get("salary")));
		
		List<EmployeeDetails> employees = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(10, employees.size());
	}
}

