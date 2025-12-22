use `m3_c0925g1`;
select * from class;

-- Câu 1 --
with class_table as(
	select c.name, count(s.id) as number_student
	from class c 
	left join student s on c.id=s.class_id
	group by c.name
)
select * from class_table
where number_student= ( select max(number_student) from class_table)
;

-- Câu 2 --
with class_table as(
	select c.name, max(s.score) as max_score
	from class c 
	left join student s on c.id=s.class_id
	group by c.name
)
select * from class_table
where max_score = ( select max(max_score) from class_table)
;

-- Câu 3 --
select name, score, rank() over(order by score desc) as ranking
from student;







