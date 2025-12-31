create database demo;
use demo;
create table products(
id int auto_increment,
product_code varchar(50),
product_name varchar(50),
product_price double unsigned,
product_quantity int unsigned,
product_desc varchar(255),
product_status tinyint,
primary key (id),
unique index product_code ( product_code),
index product_info (product_name, product_price)
);
explain select * from products where product_name ='Book';
create view view_product as
select * from products
where product_name ='Book'
;
select * from view_product;

delimiter //
create procedure add_product(
p_product_code varchar(50),
p_product_name varchar(50),
p_product_price double unsigned
)
begin
insert into products (product_code, product_name,product_price) 
values (p_product_code, p_product_name , p_product_price)
;
end //
delimiter ;

call add_product ( 'B3', 'computer', 50000);

delimiter //
create procedure del_product (del_id int)
begin
delete from products where id=del_id;
end //
delimiter ;

call del_product(3);
call add_product('B2','Book',8000);



