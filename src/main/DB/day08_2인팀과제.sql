drop database if exists todo;
create database todo;
use todo;

drop table if exists todolist;
create table todolist(
	content varchar(100),
    date date,
    state boolean default false,
    no int auto_increment,
    primary key(no)
);

select * from todolist;