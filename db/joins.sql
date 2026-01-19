CREATE TABLE departments
(
    id   serial PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE employees
(
    id             serial PRIMARY KEY,
    name           VARCHAR(100),
    departments_id INT REFERENCES departments (id)
);


INSERT INTO departments (name)
VALUES ('IT'),
       ('Finance'),
       ('HR'),
       ('Security'),
       ('Sales');

INSERT INTO employees (name, departments_id)
VALUES ('Anton', 1),
       ('Milena', 2),
       ('Michael', 3),
       ('Anna', 4),
       ('George', NULL);

-- Используя left join найти департаменты, у которых нет работников
SELECT d.name
FROM departments d
         LEFT JOIN employees e ON d.id = e.departments_id
WHERE e.id IS NULL;

-- Используя left и right join написать запросы, которые давали бы одинаковый результат
-- (порядок вывода колонок в эти запросах также должен быть идентичный).

SELECT d.name
FROM departments d
         LEFT JOIN employees e on d.id = e.departments_id;

SELECT d.name
FROM employees e
         RIGHT JOIN departments d on d.id = e.departments_id;

-- . Создать таблицу teens с атрибутами name, gender и заполнить ее.
-- Используя cross join составить все возможные разнополые пары. Исключите дублирование пар вида Вася-Маша и Маша-Вася.
-- Создать таблицу teens
CREATE TABLE teens
(
    id     SERIAL PRIMARY KEY,
    gender VARCHAR(6),
    name   VARCHAR(25)
);

INSERT INTO teens (name, gender)
VALUES ('Anton', 'Male'),
       ('Milena', 'Female'),
       ('Bred', 'Male'),
       ('Angelina', 'Female'),
       ('Boris', 'Male'),
       ('Ekaterina', 'Female'),
       ('Vyacheslav', 'Male'),
       ('Svetlana', 'Female');

SELECT t1.name, t2.name
FROM teens t1
         CROSS JOIN teens t2
WHERE t1.gender = 'Male'
  AND t2.gender = 'Female';