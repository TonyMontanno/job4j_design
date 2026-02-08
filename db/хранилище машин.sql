CREATE TABLE car_bodies
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE car_engines
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE car_transmissions
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE cars
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100),
    body_id         INT REFERENCES car_bodies (id),
    engine_id       INT REFERENCES car_engines (id),
    transmission_id INT REFERENCES car_transmissions (id)
);

INSERT INTO car_bodies (name)
VALUES ('Седан'),
       ('Хэтчбек'),
       ('Универсал'),
       ('Внедорожник'),
       ('Купе'),
       ('Кабриолет');

INSERT INTO car_engines (name)
VALUES ('Бензин 1.6L'),
       ('Бензин 2.0L'),
       ('Дизель 2.0L'),
       ('Электрический'),
       ('Гибрид');

INSERT INTO car_transmissions (name)
VALUES ('Механическая'),
       ('Автоматическая'),
       ('Роботизированная'),
       ('Вариатор');

INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES ('Toyota Camry', 1, 2, 2),                       -- Седан, Бензин 2.0L, Автомат
       ('Volkswagen Golf', 2, 1, 1),                    -- Хэтчбек, Бензин 1.6L, Механика
       ('BMW X5', 4, 3, 2),                             -- Внедорожник, Дизель 2.0L, Автомат
       ('Tesla Model 3', 1, 4, 2),                      -- Седан, Электрический, Автомат
       ('Skoda Octavia', 3, 1, 3),                      -- Универсал, Бензин 1.6L, Робот
       ('Загадочный автомобиль', NULL, 2, 2),           -- Без кузова
       ('Автомобиль-велосипед', 2, NULL, 1),            -- Без двигателя
       ('Машина на нейтралке', 1, 1, NULL),             -- Без коробки
       ('Призрачный автомобиль', NULL, NULL, NULL);     -- Вообще без деталей


-- Вывести список всех машин и все привязанные к ним детали. Нужно учесть, что каких-то деталей машина может и не содержать.
-- В таком случае значение может быть null при выводе (например, название двигателя null);
SELECT c.id,
       c.name AS car_name,
       b.name AS body_type,
       e.name AS engine_type,
       t.name AS transmission_type
FROM cars c
         LEFT JOIN car_bodies b ON c.body_id = b.id
         LEFT JOIN car_engines e ON c.engine_id = e.id
         LEFT JOIN car_transmissions t ON c.transmission_id = t.id;


-- Вывести кузова, которые не используются НИ в одной машине.
SELECT car_bodies.*
FROM car_bodies
         LEFT JOIN cars on car_bodies.id = cars.body_id
WHERE cars.body_id IS NULL;


-- Вывести двигатели, которые не используются НИ в одной машине.
SELECT car_engines.*
FROM car_engines
        LEFT JOIN cars on car_engines.id = cars.engine_id
WHERE cars.engine_id IS NULL;


-- Вывести коробки передач, которые не используются НИ в одной машине
SELECT car_transmissions.*
FROM car_transmissions
        LEFT JOIN cars ON car_transmissions.id = cars.transmission_id
WHERE cars.transmission_id IS NULL;
