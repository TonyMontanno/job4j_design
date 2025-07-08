create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('Common Carp', 15000, '1850-01-15'),
       ('Koi fish', 18000, '1823-05-20'),
       ('Goldfish', 10000, '1875-09-30'),
       ('Clownfish', 12000, '1945-12-18'),
       ('Shark', 20000, '1960-04-25'),
       ('Tiger', 8000, '1935-08-14'),
       ('Elephant', 21000, '1977-06-30'),
       ('Strange fish', 14000, NULL),
       ('Giant Tortoise', 15000, '1898-11-10'),
       ('Eagle', 16000, NULL),
       ('Dolphin', 17000, '1920-03-05');

SELECT * FROM fauna WHERE name LIKE '%fish%';
SELECT * FROM fauna WHERE avg_age BETWEEN 10000 AND 21000;
SELECT * FROM fauna WHERE discovery_date ISNUll;
SELECT * FROM fauna WHERE discovery_date < '01.01.1945';

