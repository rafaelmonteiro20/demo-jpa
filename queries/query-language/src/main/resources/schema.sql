CREATE TABLE IF NOT EXISTS department (
  id BIGINT NOT NULL PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS employee (
    id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    department_id BIGINT,
    
    PRIMARY KEY (id),
    FOREIGN KEY (department_id) REFERENCES department (id)
);

CREATE TABLE IF NOT EXISTS employee_phone (
	employee_id BIGINT NOT NULL,
	type VARCHAR(8) NOT NULL,
	number VARCHAR(15) NOT NULL,
	
	PRIMARY KEY (employee_id, type),
	FOREIGN KEY (employee_id) REFERENCES employee (id)
);

CREATE TABLE IF NOT EXISTS address (
	employee_id BIGINT NOT NULL,
	street VARCHAR(40) NOT NULL,
	city VARCHAR(30) NOT NULL,
	state VARCHAR(30) NOT NULL,
	
	PRIMARY KEY (employee_id),
	FOREIGN KEY	(employee_id) REFERENCES employee (id)
);

CREATE TABLE IF NOT EXISTS project (
	id BIGINT NOT NULL,
	name VARCHAR(50) NOT NULL,
	type VARCHAR(10) NOT NULL,
	qa_rating INT,
	
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS project_employee (
	project_id BIGINT NOT NULL,
	employee_id BIGINT NOT NULL,
	
	PRIMARY KEY (project_id, employee_id),
	FOREIGN KEY	(project_id) REFERENCES project (id),
	FOREIGN KEY	(employee_id) REFERENCES employee (id)
);

