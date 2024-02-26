drop database if exists day9member;
create database day9member;
use day9member;

drop table if exists member;
create table member(
	mno	int primary key auto_increment,
    mname varchar(20),
    mphone varchar(20)
);

drop table if exists salary;
create table salary(
	sno int primary key auto_increment,
    mno int,
    scontent varchar(20),
    salary int,
    constraint foreign key(mno) references member(mno) on delete cascade
);
select * from member;
insert into member values (0,'안녕','번호');
update member set mname = ?,mphone = ?;
delete from member where mno =1;