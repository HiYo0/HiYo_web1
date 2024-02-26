drop database if exists test10;
create database test10;
use test10;

# 1. 회원테이블
drop table if exists member;
create table member(            # 아이돌 그룹
   mid char(8) not null ,         # 식별키       최대 8자리
    mname varchar(10) not null ,   # 그룹명      최대 10자리
    mnumber int not null ,         # 인원수      정수 +-21억정도
    maddr char(2) not null ,       # 지역      최대 2자리
    mphone1 char(3) ,            # 지역번호   최대 2자리 
    mphone2 char(8) ,            # 전화번호    최대 8자리
    mheight smallint ,            # 평균키       정수 +-3만정도
   mdebut date ,               # 데뷔일       yyyy-mm-dd 
    primary key ( mid )            # 제약조건 
);
# 2. 구매테이블
drop table if exists buy;
create table buy(
   bnum int auto_increment ,          # 구매번호   정수    자동번호 부여 
    mid char(8),                  # 구매자      FK 
    bpname char(6) not null ,         # 제품명      최대 6자리 
    bgname char(4) ,                # 분류명       최대   4자리
    bprice int not null ,            # 가격       정수 
    bamout smallint not null ,         # 구매수량   정수 
    primary key(bnum) ,               # 제약조건 
    foreign key ( mid ) references member(mid) # 제약조건 
);

# 샘플데이터 ==================================================== #
INSERT INTO member VALUES('TWC', '트와이스', 9, '서울', '02', '11111111', 167, '2015.10.19');
INSERT INTO member VALUES('BLK', '블랙핑크', 4, '경남', '055', '22222222', 163, '2016.08.08');
INSERT INTO member VALUES('WMN', '여자친구', 6, '경기', '031', '33333333', 166, '2015.01.15');
INSERT INTO member VALUES('OMY', '오마이걸', 7, '서울', NULL, NULL, 160, '2015.04.21');
INSERT INTO member VALUES('GRL', '소녀시대', 8, '서울', '02', '44444444', 168, '2007.08.02');
INSERT INTO member VALUES('ITZ', '잇지', 5, '경남', NULL, NULL, 167, '2019.02.12');
INSERT INTO member VALUES('RED', '레드벨벳', 4, '경북', '054', '55555555', 161, '2014.08.01');
INSERT INTO member VALUES('APN', '에이핑크', 6, '경기', '031', '77777777', 164, '2011.02.10');
INSERT INTO member VALUES('SPC', '우주소녀', 13, '서울', '', '88888888', 162, '2016.02.25');
INSERT INTO member VALUES('MMU', '마마무', 4, '전남', '', '99999999', 165, '2014.06.19');

INSERT INTO buy VALUES(NULL, 'BLK', '지갑', NULL, 30, 2);
INSERT INTO buy VALUES(NULL, 'BLK', '맥북프로', '디지털', 1000, 1);
INSERT INTO buy VALUES(NULL, 'APN', '아이폰', '디지털', 200, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '아이폰', '디지털', 200, 5);
INSERT INTO buy VALUES(NULL, 'BLK', '청바지', '패션', 50, 3);
INSERT INTO buy VALUES(NULL, 'MMU', '에어팟', '디지털', 80, 10);
INSERT INTO buy VALUES(NULL, 'GRL', '혼공SQL', '서적', 15, 5);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 2);
INSERT INTO buy VALUES(NULL, 'APN', '청바지', '패션', 50, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 1);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 4);

# 샘플데이터END ==================================================== #

# select * from member;
# select * from buy;
# select mid from member;

# 1. 별칭 as[ 1.가독성 , 2.필드명/테이블명 연산시 간소화 등등 ]
# select mid as 회원아이디 from member;
# select mid as 회원아이디 , mname as 그룹명 from member;
# select mid 회원아이디 , mname 그룹명 from member;
# select * from member as m;
# select * from member m;

# 2. 조건절 where [ 리터럴 : 문자처리'' ]
select * from member where mname = '블랙핑크';
select * from member where mnumber = 4;
select * from member where mnumber != 4;
select * from member where mnumber > 5;
select * from member where mnumber < 5;
select * from member where mnumber <= 5;
select * from member where mnumber <= 5;

select * from member where mheight >= 165 and mheight <= 170; 	# 165~170 사이 평균 키 그룹
select * from member where mheight between 165 and 170;
select * from member where mheight >= 165 or mnumber > 6; 		# 평균키가 165이상 이거나 멤버 6 초과이면
select * from member where maddr = '경기' or maddr = '전남' or maddr = '경남'; # 주소가 경기 이거나 전남 이거나 경남 이면
select * from member where not maddr in ('경기','전남','경남');
select * from member where not mname = '블랙핑크';

select * from member where mname like '에이%';					# '에이'로 시작하는 그룹명
select * from member where mname like '%핑크';					# '핑크'로 끝나는 그룹명
select * from member where mname like '%마%';					# '마' 가 포함된 그룹명

select * from member where mname like '에이_';					# '에이'로 시작하는 3글자 그룹명
select * from member where mname like '__핑크';					# '핑크'로 끝나는 네글자 그룹명.
select * from member where mname like '_마_';					# 두번쨰 글자가 '마' 인 세글자 그룹명.

select 
	mnumber + 10 , mnumber - 10 , mnumber * 10 , 
    mnumber / 10 , mnumber div 3 , mnumber mod 3
from member;

select * from member where mphone1 = '';
# select * from member where mphone1 = NULL; 안됨
select * from member where mphone1 is null;
#select * from member where mphone1 != null; 안됨
select * from member where mphone1 is not null;



/*
	연산자
		1. 산술연산자 : +더하기 -빼기 *곱하기 /나누기 div몫 mod나머지
        2. 비교연산자 : = 같다. != 같지않다 > 초과 < 미만 >= 이상 <= 이하
        3. 논리연산자 : and 이면서	or 이거나		not 부정
        4. 기타연산자
			- 필드명 between 시작값 and 끝값			: 시작값 부터 끝값 까지 사이이면 true
            - 필드명 in( '값' , '값' , '값' )		: 여러 값 중 하나라도 포함하면 true
            - 패턴 비교 검색
				필드명 like 패턴
					1. % : 모든 문자수 대응
                    2. _ : 개수 만큼의 문자 대응
			- null 연산
				필드명 is null
                필드명 is not null
*/
select
	5+3 as 더하기 ,	5-3 as 빼기,		5*3 as 곱하기,	5/3 as 나누기,
	5 div 3 as 몫 ,	5 mod 3 as 나머지 ,
    5 = 3 , 5 !=3 , 5 > 3 , 5 < 3 , 5 >= 3 , 5 <= 3
from dual; # dual 임시테이블

# 1. as 별칭 : 필드 나 테이블에 별칭
# 2. where 조건절
# 3. order by 필드명 : 정렬
	# 오름차순 : 1 2 3 4 , 과거 -> 최신 , a b c , ㄱ ㄴ ㄷ
	# 1. order by 필드명 (asc)		: 주어진 필드로 오름차순
    # 2. order by 필드명 desc			: 주어진 필드 기준으로 내림차순
    # 2개 이상의 정렬 : order by 필드명 정렬기준 , 필드명 정렬기준
		# 앞 정렬된 데이터에서 동일한 데이터 끼리 후 정렬
select * from member order by mdebut;
select * from member order by mdebut asc;
select * from member order by mdebut desc;
select * from member order by mdebut desc , mdebut asc;

# 4. limit : 검색 레코드 수 제한
	# 1. limit 레코드수
    # 2. limit 시작 레코드수(0부터시작) , 개수
select * from member;
select * from member limit 2;
select * from member limit 0, 3;
	# 게시판의 페이징 처리 : 페이지당 5개씩 이면 1페이지( 0 , 5 ) 2페이지 ( 5 , 5 )
select * from member limit 0,5;
select * from member limit 5,5;
select * from member order by mheight desc limit 3; # 키 상위 3명
	# where , order by , limit 같이 사용시 순서 정해져 있음.
# select * from member limit 3 where mheight >= 165;
# select * from member limit 3 order by mheight >= 165;
# 작성순서
# select 필드명 from 테이블명 where 조건절 order by 정렬필드명 asc/desc limit 시작 , 개수;

# 5. distinct : 중복제거
select maddr from member;
select distinct maddr from member;
select distinct maddr , mname from member;
select distinct maddr , mphone1 from member;
# RDBMS : 관계형 데이터베이스
	# 열 과 행으로 구성된 테이블 ㅣ 검색 결과도 테이블 : 레코드 단위
    
/*
	select : 검색 / 조인 /색인
		select 필드명 , 필드명 from 테이블명
		select * from 테이블명
			1. where                                                                                                                                           
            2. order by
            3. limit
            - 작성 순서
				select * from 테이블명 where 조건절 order by 필드명 asc/desc limit 시작번호(0) , 개수
			1. as			: 별칭
            2. distinct		: 필드값 중복 제거
*/

# 2월 20일 --------------------------------------------------------
# 1. as 별칭
# 2. where 조건절
/*
	산술 : + - * / div mod
    비교 : > < >= <= = !=
    논리 : and or not
    between 시작값 and 끝값
    in( 값1 , 값2, 값3 )
    필드명 like 패턴
		1. % 	2._
	is null
    is not null
*/
# 3. order by 정렬 asc/desc
# 4. limit 시작레코드수 , 개수
# 5. distinct
# 예제/ select distinct from 테이블명 where 조건절 order by 정렬핃드 asc/desc limit 개수;
	# ------------
# -.
select bamout from buy; 			# 검색
select distinct bamout from buy;	# 중복제거 검색
select bamout+5 from buy;			# 연산

# 1. sum( 필드명 )		: 필드의 총합계
select sum(bamout) from buy;
# 2. avg( 필드명 )		: 필드의 평균
select avg(bamout) from buy;
# 3. max( 필드명 )		: 필드의 최댓값
select max(bamout) from buy;
# 4. min( 필드명 )		: 필드의 최솟값
select min(bamout) from buy;
# 5-1. count( 필드명 )	:
# 5-2. count( * )		:
select count( bgname ) from buy;	# 9
select count( * ) from buy;			# 12
/*
	집계함수.
		1. sum( 필드명 ) : 필드 합계
        2. avg( 필드명 ) : 필드 평균
        3. max( 필드명 ) : 필드 최댓값
        4. min( 필드명 ) : 필드 최솟값
        5. count( 필드명 ) : 필드 레코드 수 ( null 제외 )
			count( * ) : 레코드 수 ( null 포함 )
	
    전체집계
*/
# 1. 총 판매수량 합계
select sum( bamout ) from buy;		# 레코드수 : 1
select mid from buy;				# 레코드수 : 여러개

# 2. 회원아이디 별로 판매수량 합계 / 회원마다 판매수량 합계 !!!! : RDBMS 표
	# select sum( bamout ) , mid from buy; # 레코드 수 가 다르면 불가능
#
select mid from buy group by mid;		# mid 필드 그룹
select distinct mid from buy;			# mid 필드 중복 제거

# 2.
select mid as 구매자 , sum( bamout ) as 총수량 from buy group by mid;
# select distinct mid , sum( bamout ) from buy;		# 불가능

# 3. 회원 아이디 별(그룹) 로 총매출액[ 판매가격 * 판매수량 ]
# 3-1 전체 총매출액
select sum( bamout * bprice ) from buy;
select sum( bamout * bprice ) as 총매출액 , mid 회원아이디 from buy group by mid;

# 4. 회원 아이디 별(그룹) 로 판매수량 평균
# 4-1. 전체 판매수량 평균
select avg( bamout ) from buy;
# select avg( bamout ) as 평균구매수량 , mid 회원아이디 from buy;	# 오류
select avg( bamout ) as 평균구매수량 , mid 회원아이디 from buy group by mid;

# 5-1. 구매자 명단
select mid as 구매자 from buy;
# 5-2. 구매자 그룹
select mid as 구매자 from buy group by mid;
# 5-3. 
# select mid 구매자 , bamout from buy group by mid; # 레코드수 다름

# 6. 회원아이디 별로 총매출액 이 1000 이상 검색
select * from buy;
select (bamout * bprice) from buy;			# 각 판매별 매출액
select sum(bamout * bprice) from buy;		# 각 판매별 총매출액
select * from buy where (bamout * bprice) >=1000;	# 각 판매별 매출액이 1000 이상
select * from buy where bamout * bprice; 
				# where 뒤에는 논리 결과물 T/F 만 가능
	# select * from buy where sum(bamout * bprice);	# 오류

#
select mid 구매자 , sum(bamout * bprice) 총매출액
from buy group by mid having sum(bamout * bprice) >=1000;

	#	having 그룹에 해당하는 조건절 vs where 그룹에 해당하지 않는 조건절

select mid 구매자 , sum(bamout * bprice) 총매출액
from buy where sum(bamout * bprice) >=1000 group by mid;

# 구매수량이 3개 이상인 회원아이디 별로 총매출액 이 1000 이상 검색
select * from buy;
select * from buy where bamout >= 3;
select
	sum(bamout * bprice),mid
    from buy
    where bamout >= 3
    group by mid
    having sum(bamout * bprice)>=1000;
/*
	select
		필드명 , * , 필드명 as 별칠 , distinct 필드명
	from
		테이블명 , inner join
	on
		조인조건
	where
		일반조건
	group by
		그룹 필드
	having
		그룹 조건
	order by
		정렬 필드 asc / desc
	limit
		레코드수제한
*/

# 2월 20일 End ----------------------------------------------------
select * from buy;


