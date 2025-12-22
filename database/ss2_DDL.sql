create database management;
use management;
create table customer(
id int auto_increment,
name varchar(50),
age int check (age >0),
primary key (id)
);
create table product(
id int auto_increment,
name varchar(50),
price int check ( price>0),
primary key (id)
);
create table orders(
id int auto_increment,
cid int,
date date,
price int check (price>0),
primary key(id),
constraint foreign key(cid) references customer (id)
);
create table orderdetail(
oid int,
pid int,
orderQTY varchar(50),
primary key(oid,pid),
constraint foreign key(oid) references orders(id),
constraint foreign key(pid) references product(id)
);

drop database management;