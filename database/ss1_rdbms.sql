create database bai1;
use bai1;
create table class(
id int primary key auto_increment,
name varchar(50)
);
create table teacher(
id int primary key auto_increment,
name varchar(50), 
age int check (age>6),
country varchar(50)
);

drop database bai1;