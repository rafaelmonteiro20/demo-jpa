DELETE FROM address;
DELETE FROM employee_phone;
DELETE FROM employee;
DELETE FROM department;

INSERT INTO department (id, name) values (1, 'ADMINISTRATIVE');
INSERT INTO department (id, name) values (2, 'HR');
INSERT INTO department (id, name) values (3, 'TI');
INSERT INTO department (id, name) values (4, 'SUPPORT');
INSERT INTO department (id, name) values (5, 'SALES');

INSERT INTO employee (id, name, salary, department_id) VALUES (1,  'SMITH',   800, 4);
INSERT INTO employee (id, name, salary, department_id) VALUES (2,  'WARD' ,  1600, 5);
INSERT INTO employee (id, name, salary, department_id) VALUES (3,  'ALLEN',  1250, 4);
INSERT INTO employee (id, name, salary, department_id) VALUES (4,  'JONES',  2975, 4);
INSERT INTO employee (id, name, salary, department_id) VALUES (5,  'SCOTT',  3000, 3);
INSERT INTO employee (id, name, salary, department_id) VALUES (6,  'ANA',    1500, 2);
INSERT INTO employee (id, name, salary, department_id) VALUES (7,  'JAMES',   950, 1);
INSERT INTO employee (id, name, salary, department_id) VALUES (8,  'SOPHIA',  800, 1);
INSERT INTO employee (id, name, salary, department_id) VALUES (9,  'CLARK',  2850, 3);
INSERT INTO employee (id, name, salary, department_id) VALUES (10, 'MARTIN', 4000, 3);

INSERT INTO employee_phone (employee_id, type, number) VALUES (1, 'HOME', '111 1111111');
INSERT INTO employee_phone (employee_id, type, number) VALUES (1, 'CELL', '111 2222222');
INSERT INTO employee_phone (employee_id, type, number) VALUES (2, 'CELL', '222 2222222');
INSERT INTO employee_phone (employee_id, type, number) VALUES (6, 'WORK', '333 3333333');
INSERT INTO employee_phone (employee_id, type, number) VALUES (9, 'HOME', '444 4444444');

INSERT INTO address (employee_id, street, city, state) VALUES (1, 'A', 'NOVA IORQUE', 'NOVA IORQUE');
INSERT INTO address (employee_id, street, city, state) VALUES (2, 'B', 'BOSTON',    'MASSACHUSETTS');
INSERT INTO address (employee_id, street, city, state) VALUES (7, 'C', 'NOVA IORQUE', 'NOVA IORQUE');


