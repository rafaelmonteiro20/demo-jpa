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
