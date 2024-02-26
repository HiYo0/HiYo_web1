drop database if exists java;
create database java;
use java;

drop table if exists member;
create table member(
	mno int primary key auto_increment,					-- 번호 가 식별키(pk)로사용( not null과 unique 포함됨)
    -- auto_increment = 자동 번호부여 = pk에서만 가능
    mid	varchar(30) not null unique,		-- 아이디
    mpw	varchar(30) not null,				-- 비밀번호
    mphone char(13) not null,				-- 전화번호 010-0000-0000 형식
    mdate datetime default now()			-- 가입날짜.
);
# insert into member(mid , mpw, mphone)values('qwe3','qwe3','010-0000-0000'); ##########지워야함

# board 설계
drop table if exists category;
create table category(
   cno int not null unique auto_increment,
    cname varchar(50) not null,
    primary key(cno)
);
# insert into category(cname)value('카테고리3'); ########################지워야함
# update category set cname = '리뷰' where cname = '카테고리3';########################지워야함


drop table if exists board;
create table board(
   bno int not null unique auto_increment,
   cno int not null,      # 게시물 카테고리명
    btitle varchar(50) not null,      # 게시물 제목
    bcontent text not null,            # 게시물 내용
    mno int not null,               # 게시물 작성자
    bdate datetime default now(),      # 게시물 작성일
    bcount int,                     # 게시물 조회수
    primary key(bno),
    foreign key(mno) references member(mno),
    foreign key(cno) references category(cno)
);
# insert into board(cno,btitle,bcontent,mno,bcount) value(2,'제목2','내용2',2,1); ####################### 지울예정


drop table if exists boardsub;
create table boardsub(
   bsubno int not null auto_increment,
   bno int not null,
    bsubcontent text,               # 댓글내용
    mno int not null,            # 댓글 작성자
    primary key(bsubno),
    foreign key(bno) references board(bno),
    foreign key(mno) references member(mno)
);

select * from member;
select * from board;
select * from boardsub;
select*from category;
select cname from category;



