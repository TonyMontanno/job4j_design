create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

INSERT INTO devices (name, price) VALUES ('Phone', 10000.0), ('Laptop', 20000.0), ('HeadPhone', 5000.0);
INSERT INTO people (name) VALUES ('Ivan'), ('Alex'), ('John');
INSERT INTO devices_people (device_id, people_id) VALUES
                                                      (1,1),
                                                      (1,2),
                                                      (1,3),
                                                      (2,2),
                                                      (2,1),
                                                      (3,3),
                                                      (3,2);

SELECT avg(price) AS "Средняя цена устройства" FROM devices;

SELECT p.name AS "Имя владельца", AVG(d.price) AS "Средняя цена устройства"
FROM  people p
          JOIN devices_people dp ON p.id = dp.people_id
          JOIN devices d ON dp.device_id = d.id
GROUP BY  p.id, p.name
HAVING AVG(d.price) > 5000;
