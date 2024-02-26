drop database if exists hiyo0;
create database hiyo0;
use hiyo0;

drop table if exists box;
create table box(
	bno int auto_increment primary key,
    bname varchar(30),
    bcontent text,
    bip text
);
select * from box;
# insert into box(bname,bcontent) values('안녕','안녕2');