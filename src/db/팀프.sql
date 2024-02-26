drop database if exists team3;
create database team3;
use team3;

drop table if exists member;
create table member( # 회원
	member_pk int primary Key auto_increment,
    id varchar(10) unique not null,
    pw varchar(20) not null,
    e_mail varchar(30),
    phone varchar(13) not null,
    name varchar(10) not null
);

drop table if exists 숙소;
create table 숙소(
	숙소_pk int auto_increment primary key,
    숙소이름 varchar(20) not null,
    회원_pk int not null,
    지역 varchar(10) not null,
    최대인원 int not null,
    
    foreign key( 회원_pk ) references 회원( 회원_pk ) 
);
insert into 숙소(숙소이름,회원_pk,지역,최대인원) value('a',0,'a',0);
select * from 숙소;

drop table if exists 날짜;
create table 날짜(
    날짜_pk int primary key,
    날짜 date not null,
    숙소_pk int,
    일박당가격 int not null,
    
    foreign key(숙소_pk) references 숙소(숙소_pk)
);

drop table if exists 예약;
create table 예약(
	예약_pk int primary key,
	회원_pk int,
    예약인원 int,
    승인상태 int,
    
    foreign key(회원_pk) references 회원(회원_pk)
);
