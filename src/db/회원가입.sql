# DAY18 회원가입 게시판
# 1. 데이터베이스 생성
drop database if exists java;
create database java;
use java;

# 2. (회원가입 테이블)
drop table if exists member;
create table member(
	mno int primary key auto_increment,					-- 번호 가 식별키(pk)로사용( not null과 unique 포함됨)
    -- auto_increment = 자동 번호부여 = pk에서만 가능
    mid	varchar(30) not null unique,		-- 아이디
    mpw	varchar(30) not null,				-- 비밀번호
    mphone char(13) not null,				-- 전화번호 010-0000-0000 형식
    mdate datetime default now()			-- 가입날짜.
);

select *from member;

# 회원가입
	# insert into member(mid , mpw, mphone)values(값1,값2,값3);
insert into member(mid , mpw, mphone)values('qwe','qwe','010-0000-0000');
	# insert into member(mid , mpw, mphone)values(?,?,?);
    
# (select) 아이디 중복체크/검사/비교
select * from member;
	# select mid from member;
select mid from member;
	# select mid from member where mid = '찾을아이디'; ( whrer 조건식 : 필드명 = 값 )
select mid from member where mid = ?;

# 3. R(select) 로그인	: 아이디와 비밀번호 비교 and( java && ) , or (java || )
select * from member where mid = '123123123' and mpw = '123123123';
# select * from member where mid = ? and mpw = ?;

# 4. R(select) 회원번호 찾기 : 아이디에 해당하는 회원번호 출력
select mno from member where mid = '123123123';
# select mno from member where mid = ? ;