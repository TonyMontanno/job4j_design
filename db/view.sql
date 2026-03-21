CREATE TABLE students
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50),
    age  INTEGER
);

INSERT INTO students (name, age)
VALUES ('Иван Иванов', 19);
INSERT INTO students (name, age)
VALUES ('Петр Петров', 23);
INSERT INTO students (name, age)
VALUES ('Олег Еременко', 17);
INSERT INTO students (name, age)
VALUES ('Михаил Рыбаков', 25);

CREATE TABLE authors
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO authors (name)
VALUES ('Александр Пушкин');
INSERT INTO authors (name)
VALUES ('Николай Гоголь');

CREATE TABLE books
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(200),
    author_id INTEGER REFERENCES authors (id)
);

INSERT INTO books (name, author_id)
VALUES ('Евгений Онегин', 1);
INSERT INTO books (name, author_id)
VALUES ('Капитанская дочка', 1);
INSERT INTO books (name, author_id)
VALUES ('Дубровский', 1);
INSERT INTO books (name, author_id)
VALUES ('Мертвые души', 2);
INSERT INTO books (name, author_id)
VALUES ('Вий', 2);

CREATE TABLE orders
(
    id         SERIAL PRIMARY KEY,
    active     BOOLEAN DEFAULT true,
    book_id    INTEGER REFERENCES books (id),
    student_id INTEGER REFERENCES students (id)
);

INSERT INTO orders (book_id, student_id)
VALUES (1, 1);
INSERT INTO orders (book_id, student_id)
VALUES (3, 1);
INSERT INTO orders (book_id, student_id)
VALUES (5, 2);
INSERT INTO orders (book_id, student_id)
VALUES (4, 1);
INSERT INTO orders (book_id, student_id)
VALUES (2, 2);



CREATE VIEW show_students_over_20_with_max_books  AS
SELECT s.name, s.age, COUNT(b.id) AS total_books
FROM students s
         JOIN orders o ON s.id = o.student_id
         JOIN books b ON o.book_id = b.id
WHERE s.age > 20
GROUP BY s.id, s.name, s.age
HAVING COUNT(b.id) >= 2
ORDER BY total_books DESC, s.age DESC;