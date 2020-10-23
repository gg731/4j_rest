create table if not exists person
(
    id identity primary key not null,
    login    varchar(2000),
    password varchar(2000)
);

create table if not exists employee
(
    id identity primary key not null,
    name varchar(2000),
    last varchar(2000),
    date TIMESTAMP DEFAULT NOW
);

CREATE TABLE if not exists employee_persons
(
    id identity primary key not null,
    id_employee int,
    id_person   int,
    FOREIGN KEY (id_employee) REFERENCES employee (id),
    FOREIGN KEY (id_person) REFERENCES person (id)
);


insert into person (login, password)
values ('parsentev', '123');
insert into person (login, password)
values ('ban', '123');
insert into person (login, password)
values ('ivan', '123');

insert into employee (name, last, date)
values ('name1', 'last1', CURRENT_TIMESTAMP);
insert into employee (name, last, date)
values ('name2', 'last2', CURRENT_TIMESTAMP);

insert into employee_persons(id_employee, id_person)
values (1, 1);
insert into employee_persons(id_employee, id_person)
values (2, 2);