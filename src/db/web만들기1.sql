drop table if exists todo;
create table todo(
	id int primary key auto_increment,
    content varchar(30),
    deadline date,
    state tinyint
);
select * from todo;