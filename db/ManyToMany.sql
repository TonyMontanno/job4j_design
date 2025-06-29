-- many to many (Множество учеников и множество учителей)

create table students(
    id serial primary key,
    name varchar(255)
);

create table teachers (
    id serial primary key,
    name varchar(255)
);

create table students_teachers (
	id serial primary key,
	student_id int references students(id),
	teacher_id int references teachers(id)
);


insert into students (name) values ('Ivan');
insert into students (name) values ('Stepan');
insert into students (name) values ('Maria');

insert into teachers (name) values ('Sergey Mihaylovich');
insert into teachers (name) values ('Maksim Aleksandrovich');
insert into teachers (name) values ('Egor Petrovich');

insert into students_teachers(student_id, teacher_id) values (1, 1);
insert into students_teachers(student_id, teacher_id) values (1, 2);
insert into students_teachers(student_id, teacher_id) values (1, 3);
insert into students_teachers(student_id, teacher_id) values (2, 1);
insert into students_teachers(student_id, teacher_id) values (2, 2);
insert into students_teachers(student_id, teacher_id) values (3, 3);

select * from students;
select * from teachers;

select * from students_teachers where student_id = 1; 
select * from students_teachers where teacher_id = 2; 


