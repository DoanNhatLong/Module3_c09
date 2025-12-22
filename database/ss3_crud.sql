use sale_management;
insert into product(pname,pprice) values ('May Giat',3),('Tu Lanh',5),('Dieu Hoa',7),('Quat',1), ('Bep Dien',2);
insert into orderdetail values (1,1,3),(1,3,7),(1,4,2),(2,1,1),(3,1,8),(2,5,4),(2,3,3);
select * from orderdetail;
select c.cname ,p.pname 
from customer c
join `order` o on c.cid=o.cid 
join orderdetail od on od.oid=o.oid
join product p on p.pid=od.pid
; 
select c.cname ,p.pname
from customer c
left join `order` o on c.cid=o.cid 
left join orderdetail od on od.oid=o.oid
left join product p on p.pid=od.pid
where pname is null
; 
select od.oid, o.odate, (p.pprice * od.orderQTY) as total
from orderdetail od
join `order` o on od.oid=o.oid
join product p on p.pid=od.pid
;
