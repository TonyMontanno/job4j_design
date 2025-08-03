create table book(
    id serial primary key,
    name varchar(255)
);

create table author(
    id serial primary key,
    name varchar(255),
    book_id int references book(id) unique
);

insert into book(name) values ('Мартин Иден');
insert into book(name) values ('Война и мир');
insert into book(name) values ('Осознанность');
insert into book(name) values ('Новая земля');

insert into author(name, book_id) values ('Джек Лондон', 1);
insert into author(name, book_id) values ('Лев Толстой', 2);
insert into author(name, book_id) values ('Ошо', 3);
insert into author(name, book_id) values ('Экхарт Толле', 4);
insert into author(name) values ('Неизвестный автор 1');
insert into author(name) values ('Неизвестный автор 2');

select * from author inner
join book on author.book_id = book.id;

select a.name, b.name from author as a
join book as b on a.book_id = b.id;

select a.name as "Имя автора", b.name as "Название произведения" from author as a
join book as b on a.book_id = b.id;