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
