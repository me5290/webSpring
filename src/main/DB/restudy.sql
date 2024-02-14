drop database if exists restudy;
create database restudy;
use restudy;

drop table if exists article;
create table article(
	id int auto_increment,
    title varchar(30),
    content varchar(100),
    primary key(id)
);

select * from article;