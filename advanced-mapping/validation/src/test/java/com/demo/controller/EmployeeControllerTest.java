package com.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.PayloadExtractor;
import com.demo.controller.handler.error.Error;
import com.demo.model.Employee;
import com.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handling Standard Spring MVC Exceptions
 * 
 * https://github.com/rponte/leilao-web-spring-boot-1.5.x
 * 
 */

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@Import({ EmployeeService.class })
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper jsonMapper;
	
	private PayloadExtractor payloadExtractor;
	
	@Before
	public void init() {
		this.payloadExtractor = new PayloadExtractor(jsonMapper);
	}
	
	@Test
	public void testValidation() throws Exception {
		
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("RAFAEL MONTEIRO RAFAEL MONTEIRO");
		employee.setBirthDate(LocalDate.now().plusDays(1));
		
		mockMvc.perform(post("/employees")
					.contentType(MediaType.APPLICATION_JSON)
					.content(toJson(employee)))
			.andExpect(status().isBadRequest())
			.andDo(payloadExtractor);
		
		List<Error> errors = payloadExtractor.asListOf(Error.class);
		assertThat(errors)
			.contains(new Error("name", "size must be between 0 and 30"))
			.contains(new Error("birthDate", "must be a past date"));
	}

	@Test
	public void testSaveEmployee() throws Exception {
		
		Employee employee = new Employee(1L, "RAFAEL MONTEIRO", LocalDate.of(1989, 6, 20));
		
		mockMvc.perform(post("/employees")
					.contentType(MediaType.APPLICATION_JSON)
					.content(toJson(employee)))
			.andExpect(status().isOk())
			.andDo(payloadExtractor);
		
		Employee rafael = payloadExtractor.as(Employee.class);
		assertThat(rafael)
			.isEqualToComparingFieldByField(new Employee(1L, "RAFAEL MONTEIRO", LocalDate.of(1989, 6, 20)));
	}
	
	private String toJson(Employee employee) throws JsonProcessingException {
		return jsonMapper.writeValueAsString(employee);
	}

}
