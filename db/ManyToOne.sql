-- many to one (Один автор - множество книг)

create table author(
    id serial primary key,
    name varchar(255)
);

create table books (
    id serial primary key,
    name varchar(255),
    author_id int references author(id)
);

insert into author (name) values ('Leo Tolstoy');
insert into books (name, author_id) VALUES ('War and Peace', 1);
insert into books (name, author_id) VALUES ('Anna Karenina', 1);

select * from books;
select * from author where id in (select author_id from books);

DELETE FROM books;
DELETE FROM author WHERE id = 1; 

