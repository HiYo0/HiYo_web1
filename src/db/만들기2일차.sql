# -- 실습1 : 'test2' 데이터베이스 설계
# 1. 동일한 이름의 데이터베이스가 있으면 삭제
drop database if exists test2;

# 2. 데이터베이스 생성
create database test2;
# 2-1. 데이터베이스 확인
show databases;

# 3. 데이터베이스 활성화
use test2;

# ======================================================== #
# SQL명령어 이용한 테이블 만들기.

# 1. 테이블을 만들 위치 선택 = 데이터베이스 선택
use test2;

# 2. 테이블 생성		= create table 테이블명( 필드명 타입 , 필드명 타입 , 필드명 타입 )
create table pointtable(이름 text , 국어 int , 수학 int);
	# 각 회사별 타입 다르다. 오라클 vs MYSQL
    # JAVA에는 String 존재 MTSQL타입 String ---> text
    # MYSQL 타입
		/*
			정수 : int , 문자열 : varchar(문자길이) , 날짜 : date
        */
#3. 테이블 확인			: selet * from 테이블명;
select * from pointtable;

drop table pointtable;

# 예제 1 : 테이블 생성
# - 회원의 번호 , 회원의 ID , 회원의 PW
create table member(
	mno int , -- 회원번호 필드 ( int : 회원번호를 20억 내로 사용 )
    mid varchar(30) ,	-- 회원아이디 필드( varchar : 회원아이디 255 )
		-- char(길이)		: 고정길이		char(3)		A(1) + 남은(2)
        -- varchar(길이)	: 가변길이		varchar(3)	A(1)
	mpw varchar(20) 	-- 회원비밀번호 필드( varchar : 회원비밀번호 20글자 이내 가변길이 사용 )
);

# 2. 테이블 확인
select * from member;

# 3. 테이블 삭제
drop table member;

# 예제2 : 테이블 생성
#게시판 설계 : 게시물번호 , 게시물제목 , 게시물내용 , 조회수 , 작성일

# 1.데이터베이스 선택
use test2;
# 2. 테이블초기화 [ 동일한 테이블명이 있으면 삭제 ]
drop table if exists board;
# 3. 테이블 생성
create table board(
	bno bigint, 			-- 게시물번호
    btittle varchar(100),	-- 게시물제목( 게시물 제목을 100글자 이내 가변길이 사용 )
    bcontent longtext,		-- 게시물내용( 텍스트만 지원하는 내용 - 최대 4G )
    bview int ,				-- 게시물조회수
    bdate date				-- 게시물작성일
);
# 4. 테이블확인
select * from board;


