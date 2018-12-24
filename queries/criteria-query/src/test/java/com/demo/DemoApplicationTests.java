package com.demo;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dto.EmployeeDetails;
import com.demo.model.Address;
import com.demo.model.Department;
import com.demo.model.Employee;
import com.demo.model.PhoneType;
import com.demo.model.Project;

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
	
	@Test
	public void testJoinOperator() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Project> root = criteria.from(Project.class);
		
		Join<Project, Employee> e = root.join("employees");
		criteria.select(e);
		
		criteria.where(cb.equal(root.get("name"), "PROJECT A"));
		
		List<Employee> employees = manager.createQuery(criteria)
			.getResultList();
		
		assertEquals(2, employees.size());
	}
	
	@Test
	public void testJoinConditionWhereClause() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = cb.createQuery(Department.class);
		Root<Employee> e = criteria.from(Employee.class);
		Root<Department> d = criteria.from(Department.class);		

		criteria.distinct(true);
		criteria.select(d);
		criteria.where(cb.equal(d, e.get("department")));
		
		List<Department> departments = manager.createQuery(criteria)
			.getResultList();
		
		assertEquals(5, departments.size());
	}
	
	@Test
	public void testMapJoin() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
		Root<Employee> e = criteria.from(Employee.class);
		
		MapJoin<Employee, PhoneType, String> p = e.joinMap("phones");
		criteria.multiselect(
				e.get("name").alias("name"),
				p.key().alias("type"),
				p.value().alias("number"));
		
		criteria.where(cb.equal(p.key(), PhoneType.WORK));
		
		Tuple result = manager.createQuery(criteria).getSingleResult();
		
		assertEquals("ANA", result.get("name"));
		assertEquals(PhoneType.WORK, result.get("type"));
		assertEquals("333 3333333", result.get("number"));
	}
	
	@Test
	public void testFetchJoin() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Address> criteria = cb.createQuery(Address.class);
		Root<Address> a = criteria.from(Address.class);
		
		a.fetch("employee");
		criteria.select(a);
		
		List<Address> addresses = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(3, addresses.size());
		assertNotNull(addresses.get(0).getEmployee().getName());
	}
	
	@Test
	public void testJoinWithON() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Employee> e = criteria.from(Employee.class);
		
		Join<Employee, Department> d = e.join("department");
		d.on(cb.like(d.get("name"), "S%"));
		
		List<Employee> employees = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(4, employees.size());
	}
	
	@Test
	public void testPredicateConstructionUsingConjunction() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Employee> e = criteria.from(Employee.class);
		Join<Employee, Department> d = e.join("department");
		
		criteria.select(e);
		Predicate predicate = cb.conjunction();
		
		ParameterExpression<String> department = cb.parameter(String.class, "department");
		predicate = cb.and(predicate, cb.equal(d.get("name"), department));
		
		ParameterExpression<Double> salary = cb.parameter(Double.class, "salary");
		predicate = cb.and(predicate, cb.le(e.get("salary"), salary));
		
		criteria.where(predicate);
		
		List<Employee> employees = manager.createQuery(criteria)
				.setParameter("department", "SUPPORT")
				.setParameter("salary", 1000.0)
				.getResultList();
		
		assertEquals(1, employees.size());
	}
	
	@Test
	public void testSubquery() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Employee> e = criteria.from(Employee.class);
		
		criteria.select(e);
		
		Subquery<Double> subquery = criteria.subquery(Double.class);
		Root<Employee> root = subquery.from(Employee.class);
		subquery.select(cb.max(root.get("salary")));
		
		criteria.where(cb.equal(e.get("salary"), subquery));
		
		Employee employee = manager.createQuery(criteria)
				.getSingleResult();
		
		assertEquals("MARTIN", employee.getName());
		assertEquals(4000.0, employee.getSalary(), 0.0001);
	}
	
	@Test
	public void testSubqueryCorrelated() {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Employee> e = criteria.from(Employee.class);
		
		criteria.select(e);
		
		Subquery<Integer> subquery = criteria.subquery(Integer.class);
		Root<Employee> root = subquery.correlate(e);
		MapJoin<Employee, PhoneType, String> p = root.joinMap("phones");
		
		subquery.select(cb.<Integer>literal(1));
		subquery.where(cb.equal(p.key(), PhoneType.CELL));
		
		criteria.where(cb.exists(subquery));
		
		List<Employee> employees = manager.createQuery(criteria)
				.getResultList();
		
		assertEquals(2, employees.size());
	}

	
}

