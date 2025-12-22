use m3_c0925g1;
-- Bài 1 --
select s.id, s.name, c.name 
from student s 
join class c on s.class_id=c.id
order by s.id
;

-- Bài 2--
select s.id, s.name, c.name 
from student s 
left join class c on s.class_id=c.id
order by s.id
;

-- Bài 3 --
select * from student
where name like'%Hai' or name like'%Huynh'
;

-- Bài 4 --
select id, name, score from student where score >5;

-- Bài 5 --
select * from student
where name like'nguyen%'
;

-- Bài 6 --
select score , count(name) as quantity
from student
group by score
order by score desc
;

-- Bài 7 --
select score , count(name) as quantity
from student
where score >5
group by score
order by score desc
;

-- Bài 8 --
select score , count(name) as quantity
from student
where score >5
group by score
having quantity >=2
order by score
;

-- Bài 9 --
select * from student
order by name
;


















