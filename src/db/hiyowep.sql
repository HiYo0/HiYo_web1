drop database if exists hiyowep;
create database hiyowep;
use hiyowep;

drop table if exists member;
create table member(
	no int primary key auto_increment,
    id varchar(30) not null unique,
    pw varchar(30) not null,
    name varchar(20) not null,
    email varchar(50) not null,
    phone varchar(30) not null unique,
    img text
);


insert into member value (0,"123","123","이름","주소","010-1231-1231","이미지.png");
#update member set pw = ? , name = ? , email = ? , phone = ? , img = ? where no=?;
#delete from member where no = 0;

select * from member;
select * from member where id = 12345;
#select * from member where id=? and pw = ?;