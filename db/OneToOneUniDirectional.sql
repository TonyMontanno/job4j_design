-- one to one unidirectional (Один президент - одна страна)

create table president(
    id serial primary key,
    name varchar(255)
);

create table country (
    id serial primary key,
    name varchar(255),
    president_id int references president(id) unique
);


insert into president (name) values ('Vladimir Putin');
insert into country (name, president_id) values ('Russia', 1);

select * from president;
select * from country where president_id in (select id from president);
