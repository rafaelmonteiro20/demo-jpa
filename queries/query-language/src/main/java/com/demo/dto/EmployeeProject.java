package com.demo.dto;

public class EmployeeProject {

	private String employeeName;
	private String projectName;
	private Integer qaRating;

	public EmployeeProject(String employeeName, String projectName, Integer qaRating) {
		this.employeeName = employeeName;
		this.projectName = projectName;
		this.qaRating = qaRating;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getProjectName() {
		return projectName;
	}

	public Integer getQaRating() {
		return qaRating;
	}

}
