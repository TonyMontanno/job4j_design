-- one to one bidirectional (Один президент - одна страна)

create table president(
    id serial primary key,
    name varchar(255)
);

create table country (
    id serial primary key,
    name varchar(255),
);

create table president_country (
	id serial primary key,
	president_id int references president(id) unique,
	country_id int references country(id) unique
);


insert into president (name) values ('Vladimir Putin');
insert into president (name) values ('Donald Trump');

insert into country (name, president_id) values ('Russia', 1);
insert into country (name, president_id) values ('USA', 2);


insert into president_country (president_id, country_id) values (1, 1);
insert into president_country (president_id, country_id) values (2, 2);

select * from president;
select * from country;

select * from president_country where president_id = 1; 
select * from president_country where country_id = 2; 


