insert into student select * from (
 select 'name1', 11 union
 select 'name2', 12
) x where not exists(select * from student);
