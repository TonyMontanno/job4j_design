CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);


--   Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар
--   (нужно прибавить налог к цене товара). Действовать он должен не на каждый ряд, а на запрос (statement уровень)

CREATE
OR REPLACE FUNCTION add_tax_for_statement()
    RETURNS trigger AS
$$
    BEGIN
        UPDATE products
        SET price = price + price * 0.13
        WHERE id IN (SELECT id FROM inserted);
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';



CREATE TRIGGER add_tax_for_statement_trigger
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE add_tax_for_statement();



INSERT INTO products (name, producer, price)
VALUES ('Laptop', 'HP', 5000);
SELECT * FROM products;

DROP TRIGGER add_tax_for_statement_trigger ON products;

------------------------------------------------------------------------------------------------------------------------
--    Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара).
--    Здесь используем row уровень.

CREATE
OR REPLACE FUNCTION add_tax_for_row()
    RETURNS trigger AS
$$
    BEGIN
        NEW.price = NEW.price + NEW.price * 0.13;
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';


CREATE TRIGGER add_tax_for_row_trigger
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE add_tax_for_row();

INSERT INTO products (name, producer, price)
VALUES ('Mouse', 'Logitech', 1000),
       ('Monitor', 'Xiaomi', 10000),
       ('Book', 'SuperImmunity', 100);

SELECT * FROM products;
DROP TRIGGER add_tax_for_row_trigger ON products;


--  Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products,
--  будет заносить имя, цену и текущую дату в таблицу history_of_price.

CREATE TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

CREATE
OR REPLACE FUNCTION insert_into_history_of_price()
    RETURNS trigger AS
$$
    BEGIN
        INSERT INTO history_of_price (name, price, date)
        VALUES (NEW.name, NEW.price, now());
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';


CREATE TRIGGER insert_into_history_of_price
    AFTER INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE insert_into_history_of_price();

INSERT INTO products (name, producer, price) VALUES ('Keyboard', 'Logitech', 2000);
SELECT * FROM history_of_price;