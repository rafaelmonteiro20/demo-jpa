DELETE FROM employee;
DELETE FROM address;

INSERT INTO address (id, street, city, state) VALUES (1, 'A', 'NOVA IORQUE', 'NY');
INSERT INTO address (id, street, city, state) VALUES (2, 'B', 'BOSTON',      'MA');
INSERT INTO address (id, street, city, state) VALUES (3, 'C', 'NOVA IORQUE', 'NY');
INSERT INTO address (id, street, city, state) VALUES (4, 'D', 'LOS ANGELES', 'CA');
INSERT INTO address (id, street, city, state) VALUES (5, 'E', 'SAN DIEGO',   'CA');

INSERT INTO employee (id, name, phone, email, active, address_id) 
	VALUES (1, 'SMITH', '111 1111111', 'smith@email.com', 1, 1);

INSERT INTO employee (id, name, phone, email, active, address_id) 
	VALUES (2, 'WARD' , '222 2222222', 'ward@email.com', 1, 2);

INSERT INTO employee (id, name, phone, email, active, address_id) 
	VALUES (3, 'ALLEN',  null, 'allen@email.com', 1, 3);

INSERT INTO employee (id, name, phone, email, active, address_id) 
	VALUES (4, 'JONES', '444 4444444', null, 1, 4);

INSERT INTO employee (id, name, phone, email, active, address_id) 
	VALUES (5, 'SCOTT',  null, null, 0, 5);
