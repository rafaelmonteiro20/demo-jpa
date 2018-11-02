CREATE TABLE IF NOT EXISTS customer (
    id BIGINT NOT NULL,
    name VARCHAR(40) NOT NULL,
	document VARCHAR(20) NOT NULL,
	type VARCHAR(10) NOT NULL,
	birth_date DATE NOT NULL,
    
    PRIMARY KEY (id)
);
