use furama;
-- Bài 2--
SELECT id,name from employee where (name like 'T%' or name like 'H%' or name like'K%' ) and (char_length(name) <=15);

-- Bài 3--
select id_customer,name from customer where (timestampdiff(year, birthday, curdate())>=18 
and timestampdiff(year, birthday, curdate())<=50) and (address like '%Đà Nẵng' or address like '%Quảng Trị%');

-- Bài 4 --

select c.id_customer, c.name, count(cd.quantity) as count_contract
from customer c
join type_customer tc on tc.id_type = c.id_type
join contract co on co.id_customer = c.id_customer
join contract_detail cd on cd.id_contract = co.id_contract
where tc.type = 'Diamond'
group by c.id_customer ,c.name
order by count_contract;

select distinct c.id_customer, c.name, count(cd.quantity) over(partition by c.id_customer) as count_contract
from customer c
join type_customer tc on tc.id_type=c.id_type
join contract co on co.id_customer=c.id_customer
join contract_detail cd on cd.id_contract=co.id_contract 
where tc.type='Diamond'
order by count_contract;

-- Bài 5--
select c.id_customer, c.name, tc.type, co.id_contract, s.name, co.start, co.end, (es.price*cd.quantity) as total
from customer c 
join type_customer tc on c.id_type=tc.id_type
left join contract co on c.id_customer=co.id_customer
left join service s on s.id_service=co.id_service
left join contract_detail cd on cd.id_contract=co.id_contract
left join extra_service es on es.id=cd.id_service
order by c.id_customer, cd.id_detail
;

-- Bài 6--
select s.id_service, s.name, ts.name
from service s 
join type_service ts on s.id_type_service=ts.id_type
join contract c on c.id_service=s.id_service
where month(c.start) not in(1,2,3)
;

-- Bài 7--
select s.id_service, s.name, ts.name
from service s
join type_service ts on s.id_type_service=ts.id_type
join contract c on c.id_service=s.id_service
where year(c.start) in (2020,2021)
group by id_service
having sum(year(c.start)=2020)>0 and sum(year(c.start)=2021)=0
;

-- Bài 8 --
select distinct name from customer;
select `name` from customer group by `name`;

-- Bài 9--
select month(start) as month, count(id_customer)
from contract
group by month
order by month
;

-- Bài 10 --
select c.id_contract, c.start, c.end, c.money_first, sum(cd.quantity) as extra_service_quantity
from contract c
left join contract_detail cd on c.id_contract=cd.id_contract
group by c.id_contract
order by id_contract
;

-- bài 11 --
with temp_customer as (
	select c.id_customer, c.name 
	from customer c
	join type_customer tc on c.id_type=tc.id_type
	where tc.type='Diamond' and (c.address like '%Đà Nẵng' or c.address like '%Quảng Ngãi' )
), contract_table as(
	select c.id_customer, es.id, es.name 
	from contract c
	join contract_detail cd on cd.id_contract=c.id_contract
	join extra_service es on es.id=cd.id_service
)
select ct.id, ct.name
from contract_table ct
join temp_customer tc on ct.id_customer=tc.id_customer
;

-- Bài 12 --
with temp_contract as(
	select c.id_contract, e.name as emp_name , cus.name as cus_name, cus.phone, s.name, c.money_first
	from contract c
	join employee e on c.id_employee=e.id
	join customer cus on cus.id_customer=c.id_customer
	join service s on c.id_service=s.id_service
), temp_extra as(
	select c.id_contract, count(cd.id_service) as count_service
	from contract c
	join contract_detail cd on c.id_contract=cd.id_contract
	group by c.id_contract
)
select tc.* from temp_contract tc
join temp_extra te on tc.id_contract=te.id_contract
;

-- bài 13 --
with total as(
	select es.id, es.name, sum(cd.quantity) as total_service
	from contract_detail cd
	join extra_service es on cd.id_service =es.id
	group by es.id, es.name
)
select id, name, total_service
from total
where total_service=(
	select max(total_service) from total
)
;

-- bài 14 --
with service_table as(
	select cd.id_contract, ts.name as service_name, es.name as extra_service_name
	from contract_detail cd
	join extra_service es on es.id=cd.id_service
	join contract c on c.id_contract=cd.id_contract
	join service s on s.id_service=c.id_service
    join type_service ts on ts.id_type = s.id_type_service 
) 
select st.*, count(cd.quantity) as count_extra_service
from service_table st 
join contract_detail cd on st.id_contract=cd.id_contract
group by st.id_contract, st.service_name, st.extra_service_name
having count_extra_service =1
;

-- Bài 15 --
with employee_table as (
	select e.id, e.name, le.name as level, pe.name as part, e.phone
	from employee e 
	join level_employee le on  le.id=e.id_level
	join part_employee pe on pe.id=e.id_part
), count_table as( 
	select e.id, count(c.id_employee) as count_contract
	from employee e
	left join contract c on c.id_employee = e.id and c.start between '2020-01-01' and '2021-12-31'
	group by e.id
)
select et.* from employee_table et
join count_table ct on et.id=ct.id
where ct.count_contract <3
order by et.id
;
-- Bài 16 --
select distinct e.id, e.name, con.id_employee
from employee e
left join contract con on e.id=con.id_employee
and con.start between '2019-01-01' and '2021-12-31'
where con.id_employee is null
;
-- (Thay select bằng delete c)--

-- Bài 17 --
select c.id_customer, c.name, (s.price + cd.quantity * es.price) as total_cost
from customer c 
join type_customer tc on c.id_type=tc.id_type and tc.id_type=2
join contract con on con.id_customer = c.id_customer
join service s on con.id_service=s.id_service
join contract_detail cd on cd.id_contract=con.id_contract
join extra_service es on cd.id_service=es.id
where s.price + cd.quantity * es.price > 10000000
;

-- Bài 18 --
select cus.id_customer, cus.name, c.start
from customer cus 
join contract c on cus.id_customer=c.id_customer
where c.start <'2021-01-01'
; 

-- Bài 19 --
select es.name, sum(cd.quantity) as total
from extra_service es
join contract_detail cd on cd.id_service=es.id
join contract c on cd.id_contract=c.id_contract and year(c.start)=2020
group by es.name
having total >=10
;

-- Bài 20 --
select id_customer, name, birthday, id_person from customer
union all
select id, name, birthday, id_person from employee
;

-- Bài 21 -- 
create view v_employee as
select distinct e.* from employee e
join contract c on c.id_employee=e.id and year(c.start)=2021
where e.address like '%Huế' 
;
select * from v_employee;

-- Bài 22 --
update v_employee
set address='Đà Nẵng'
;

-- Bài 23 --
delimiter //
create procedure del_customer(p_id int)
begin
select * from customer
where id_customer=p_id
;
end //
delimiter ;
call del_customer(4);

-- Bài 25 --










