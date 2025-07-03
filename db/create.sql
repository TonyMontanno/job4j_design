create table users(
    id serial primary key,
    name text,
    role_id int references roles(id)
);

create table roles(
    id serial primary key,
    role_name text
);

create table rules(
    id serial primary key,
    rule_name text
);

create table roles_rules(
    id serial primary key,
    roles_id int references roles(id),
    rules_id int references rules(id)
);

create table items(
    id serial primary key,
    item_name text,
    user_id int references users(id),
    category_id int references categories(id),
    state_id int references states(id)
);

create table comments(
    id serial primary key,
    comment text,
    item_id int references items(id)
);

create table attaches(
    id serial primary key,
    attach_name text,
    item_id int references items(id)
);

create table states(
    id serial primary key,
    state_name text
);

create table categories(
    id serial primary key,
    category_name text
);



