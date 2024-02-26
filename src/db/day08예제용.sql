drop database if exists day08;
create database day08;
use day08;

drop table if exists board;
create table board(
	bno int primary key auto_increment,
    bcontent text,
    bwriter text,
    bpassword text
);

select * from board;
insert into board values (0 , '123','123','123');