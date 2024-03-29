/*
- 데이터베이스
	- 데이터(자료)베이스(모임) : 자료들의 모임
    - 여러 사람들이 공유하여 사용할목적으로 체계화된 데이터의 집합

- 데이터베이스 종료
	- 1. 계층형 데이터베이스
    - 2. 네트워크형 데이터베이스
    - 3. (*)관계형 데이터베이스
    - 4. key-value 데이터베이스

- 용어
	- DB : 데이터베이스
    - DB SEVER : 데이터베이스 위치하는 곳( port : 3306 ) / 서버 : 제공자 vs 클라이언트 : 자료요청
	- DBMS : 데이터베이스 관리 시스템 : 오라클 , MTSQL , MSSQL 등등 - 약간의 문법차이
    - DBA : 데이터베이스 관리자
    - SQL : 프로그래밍 언어 --> 문법

		DBA --------> DBMS --------> DB
        [개발자]		[소프트웨어]		[자료]
									0101010 2진수
			A저장	insert a

- 관계형 데이터베이스 RDBMS
	- 현재 가장 많이 사용되는 데이터베이스 종류
    - 행 과 열로 이루어진 각각의 테이블을 !!(고유값)을 참조하여 서로 종속되는 관계(=연결)를 표현

- 테이블 기본용어
	- 레코드	: /튜플/행/가로	: 관계된 데이터들의 가로의 묶음
    - 필드	: /속성/열/세로	: 가장 작은 단위 데이터
    - 테이블	: /릴레이션		: 행과 열로 이루어진 데이터 집합
    - 키		: 테이블에서 행의 식별자로 이용되는 식별 데이터


=========================================================================*/
# - SQL의 주석처리 하는 방법
    #한줄주석
	-- 한줄주석
	/*여러줄주석*/

# - 한줄 명령어 끝마침 ;(세미콜론)
# - 한줄씩 명열어 실행 : 실행 명령어 커서 위치하고 ctrl+Enter

# 1. 데이터베이스 확인
show databases;
	# 1-1 : 데이터확인 show databases;
    # 1-2 : (워크벤치) 왼쪽메뉴 -> Navigator 아래 탭에 [Schemas]클릭 -> 새로고침 버튼

# 2. 데이터베이스 생성		: create database 데이터베이스이름
create database sqldb;
#(워크벤치) 상단메뉴 에서 4번째 아이콘 : create new schema 클릭

# 3. 데이터베이스가 (로컬)저장되는 위치
show variables like 'datadir';

# 4. 데이터베이스 삭제				: drop database 삭제할데이터베이스이름;
drop database sqldb;
# 4-1 : 만약에 데이터베이스가 존재하면 삭제, 존재하지 않으면 처리x
drop database if exists sqldb;

# 5. 사용할 데이터베이스 활성화/선택	: use 사용할데이터베이스 이름
	# 데이터베이스 안에 테이블을 저장할수 있는데. 테이블 조작하기전에 먼저 데이터베이스 선택
use sqldb;

# 예1 : 데이터베이스 만들기 ============================================================ #

# 1. [ 데이터베이스 초기화 ]데이터베이스 만들기전에 혹시나 존재할수 있기때문에 삭제 먼저
drop database if exists test1;

# 2. [ 데이터베이스 생성 ] 여러개 테이블들을 저장할 데이터베이스 / 구역 만들기.
create database test1;

# 3. [ 데이터베이스 확인 ] 생성된 데이터베이스 확인
show databases;

# 4. [ 데이터베이스 사용 ] 데이터베이스를 선택해서 테이블 만들기 위해
use test1;