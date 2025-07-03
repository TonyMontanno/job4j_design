-- Роли
INSERT INTO roles (role_name)
VALUES ('admin'),
       ('user');

-- Пользователи
INSERT INTO users (name, role_id)
VALUES ('Антон', 1),
       ('Иван', 2);

-- Права
INSERT INTO rules (rule_name)
VALUES ('read'),
       ('write');

-- Права ролей
INSERT INTO roles_rules (roles_id, rules_id)
VALUES (1, 1), -- admin - read
       (1, 2), -- admin - write
       (2, 1); -- user - read

-- Категории
INSERT INTO categories (category_name)
VALUES ('Техника'),
       ('Документы');

-- Состояния
INSERT INTO states (state_name)
VALUES ('Создана'),
       ('Закрыта');

-- Заявки
INSERT INTO items (item_name, user_id, category_id, state_id)
VALUES ('Не работает монитор', 1, 1, 1),
       ('Нужна справка', 2, 2, 2);

-- Комментарии
INSERT INTO comments (comment, item_id)
VALUES ('Проверь разъём', 1),
       ('Справка готова', 2);

-- Файлы
INSERT INTO attaches (attach_name, item_id)
VALUES ('monitor.jpg', 1),
       ('spravka.pdf', 2);
