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
