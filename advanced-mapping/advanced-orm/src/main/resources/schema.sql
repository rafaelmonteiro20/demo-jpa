CREATE TABLE IF NOT EXISTS address (
	id BIGINT NOT NULL,
	street VARCHAR(40) NOT NULL,
	state VARCHAR(2) NOT NULL,
	city VARCHAR(40) NOT NULL,
	
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS employee (
    id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(80),
    active TINYINT NOT NULL DEFAULT 1,
    address_id BIGINT NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE IF NOT EXISTS customer (
    id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(80),
    addr_id BIGINT NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (addr_id) REFERENCES address (id)
);
