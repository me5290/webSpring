drop database if exists team3DB;
create database team3DB;
use team3DB;

drop table if exists team3Table;
create table team3Table(
	no int auto_increment,
    name varchar(30) not null,
    phone varchar(14) not null unique,
    primary key(no)
);

select * from team3Table;

drop table if exists team3Plus;
create table team3Plus(
	no int,
    content varchar(255),
    point varchar(30),
    date datetime default now(),
    foreign key(no) references team3Table(no) on delete cascade
);

select * from team3Plus;