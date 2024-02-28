console.log('member.js');

/*
    onclick     : 클릭할때마다
    onchange    : 값이 변경될때 마다.
    onKeyup     : 키보드 키를 때었을때.

    ------ 정규표현식 ------
    정규표현식 이란 : 특정한 규칙을 가진 문자열의 집합을 표현할때 사용하는 형식 언어
        - 주로 문자열 데이터 검사할때 사용 - 유효성검사.
        - 메소드
            정규표현식.test( 검사할대상 );
        - 형식 규칙
            /^                     : 정규표현식 시작 알림.
            $/                     : 정규표현식 끝 알림.
            { 최소길이 , 최대길이 } : 문자 길이 규칙
            [ 허용할문자 ]          : 허용 문자 규칙
                [ a-z ]                : 소문자 a ~ z 허용
                [ a-zA-Z]              : 대소문자 a ~ z 허용
                [ a-zA-Z0-9 ]          : 영 대소문자 숫자 허용
                [ a-zA-Z0-9가-힣 ]      : 영 대소문자 , 숫자 , 한글 허용
            +   : 앞 에 있는 패턴 1개 이상 반복
            ?   : 앞 에 있는 패턴 0개 혹은 1개 이상 반복
            *   : 앞 에 있는 패턴 0개 반복
                (?=.*[1개이상문자패턴])
            .   : 1개 문자
            ()  : 패턴의 그룹
            ?=  : 문자 존재 여부 판단.

            예1) /^[a-z0-9]{5,30}$/
                영소문자와 숫자 조합의 5~30글자 허용
            예2) /^[A-Za-z0-9]{5,30}$/
                영 대소문자와숫자 조합의 5~30글자 허용
            예3) 1개 이상 필수
                /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}$/
                영 대소문자 1개 이상 필수 , 숫자 1개 이상 필수
            예4) /^[가-힣]{5,20}$/
                한글 5~20글자
            예5) 000-0000-0000 또는 00-000-0000
                /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]+([0-9]{4})$/
            예6) 문자@문자.문자
                qwe@naver.com
                /^[a-zA-Z0-9_-]+[@]+[a-zA-Z0-9_-]+\.[a-zA-Z]+$/



    
*/

// ********* 유효성검사 체크 현황
let checkArray = [ false, false ,false ,false ,false]; // 아이디 , 비밀번호 , 이름 , 이메일 , 전화번호


// 4. 아이디 유효성검사. ( 아이디 입력할때마다. )
function idCheck(){
    console.log("아이디 유효성검사 함수 실행");

    // 1. 
    let id = document.querySelector('#id').value;
    console.log(id);

    // 2. 정규표현식 : 영소문자+숫자 조합의 5~30 글자 사이 규칙
    let 아이디규칙 = /^[a-z0-9]{1,30}$/;

    // 3. 정규표현식 에 따른 검사.
    console.log(아이디규칙.test(id));
    if(아이디규칙.test(id)){
        // *아이디 중복체크( ajax )
        $.ajax({ // 비동기 vs 동기
            url : "/hiyoweb/find/idcheck",
            method : "get",     // HTTP BODY -> 없다. -> 쿼리스트링
            data : { id : id }, // `/member/find/idcheck?id=${id}`
            success : function(result){ // true : 중복있다. , false : 중복없다.
                if(result){
                    document.querySelector('.idcheckbox').innerHTML = `사용중인 아이디`;
                    checkArray[0] = false; // 체크현황 변경
                }else{
                    document.querySelector('.idcheckbox').innerHTML = `중복없음`;
                    checkArray[0] = true; // 체크현황 변경
                }
            }
        })// ajax end
        
    }else{
        // 유효성 검사 결과 출력
        document.querySelector('.idcheckbox').innerHTML = `영 소문자+숫자 조합의 5~30글자 사이로 입력해주세요.`;
        checkArray[0] = false; // 체크현황 변경
    }

}//function idCheck end

// 5. 비밀번호 유효성검사
function pwcheck(){
    console.log('pwcheck 실행됨');

    // 1. 입력값 가져온다
    let pw = document.querySelector('#pw').value;
    let pwconfirm = document.querySelector('#pwconfirm').value;

    // 2. 유효성검사
    let msg = "통과";//기본값

        // 1. 비밀번호에 대한 정규표현식 : 영대소문자 1개 필수 와 숫자 1개 필수 의 조합 5 ~ 30글자
    let 비밀번호규칙 = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{1,30}$/;

    if(비밀번호규칙.test(pw)){
        if(비밀번호규칙.test(pwconfirm)){
            if(pw == pwconfirm){// 2. 비밀번호 와 비밀번호확인 이 동일한지 비교.
                msg="가입가능";
                checkArray[1] = true; // 체크현황 변경
            }else{
                checkArray[1] = false; // 체크현황 변경
                msg="비밀번호 와 비밀번호 확인 불일치";
            }
        }else{
            msg = `확인 영 대소문자+숫자 조합의 1~30글자 사이로 입력해주세요.`;
            checkArray[1] = false; // 체크현황 변경
        }
    }else{
        msg = `비밀번호 영 대소문자+숫자 조합의 1~30글자 사이로 입력해주세요.`;
        checkArray[1] = false; // 체크현황 변경
    }
        
    //
    document.querySelector('.pwcheckbox').innerHTML = msg;

}//function pwcheck end

// 6. 이름 유효성검사
function namecheck(){
    let name =document.querySelector('#name').value;    // 1. 입력값 가져온다.
    let 이름규칙 = /^[가-힣]{1,20}$/;       // 2. 정규표현식 작성한다.
    msg = "";
    if(이름규칙.test(name)){                // 3. 정규표현식 검사한다.
        msg = "통과";
        checkArray[2] = true;               // 4. 정규표현식 검사가 일치했을때.
    }else{
        checkArray[2] = false;
        msg = "한글 1~20글자";
    }

    document.querySelector('.namecheckbox').innerHTML = msg;
}// function namecheckbox End

// 7. 전화번호 유효성검사 : 000-0000-0000 또는  00-000-0000
function phonecheck(){
    let phone = document.querySelector('#phone').value;
    let 전화번호규칙 = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]+([0-9]{4})$/;
    let msg = '000-0000-0000 또는 00-000-0000 으로 입력해주세요.';
    checkArray[3] = false;

    if(전화번호규칙.test(phone)){
        msg = "통과";
        checkArray[3] = true;

    }else{
        msg = '000-0000-0000 또는 00-000-0000 으로 입력해주세요.(2)';
        checkArray[3] = false;
    }

    document.querySelector('.phonecheckbox').innerHTML = msg;
}//function phonecheck End

// 8. 이메일 유효성검사
function emailcheck(){
    let email = document.querySelector('#email').value;
    let 이메일규칙 = /^[a-zA-Z0-9_-]+[@]+[a-zA-Z0-9_-]+\.[a-zA-Z]+$/;
    msg = '이메일 형식에 맞춰주세요 ex) qwe@naver.com';
    checkArray[4] = false;
    if(이메일규칙.test(email)){
        msg= "통과";
        checkArray[4] = true;
    }else{
        msg = '이메일 형식에 맞춰주세요 ex) 이메일아이디@도메인주소';
        checkArray[4] = false;
    }
    document.querySelector('.emailcheckbox').innerHTML = msg;
}// function emailcheck End

// ======================== 유효성검사 끝 ===================== //


// 1. 회원가입
function signup(){

    // * 유효성검사 체크 현황중에 하나라도 false이면 회원가입 금지.
    for( let i = 0; i<checkArray.length; i++ ){
        if(!checkArray[i]){
            alert('입력사항들을 모두 정확히 입력해주세요.');
            return;
        }
    }
    console.log('signup()');
    
    
    
    // 1. HTML 입력값 호출 [document.querySelector() ]
    // 방법1. 데이터 하나씩 요청
        // let id = document.querySelector('#id').value;
        // let pw = document.querySelector('#pw').value;
        // let name = document.querySelector('#name').value;
        // let email =document.querySelector('#email').value;
        // let phone = document.querySelector('#phone').value;
        // let img = document.querySelector('#img').value;
    // 방법2. 폼 가져오기 바이트 로 변환해서 가져와야할 경우
    let signupForm = document.querySelectorAll('.signUpForm');
        console.log(signupForm);
    let signUpFormData = new FormData( signupForm[0] );
        // new FormData 문자데이터가 아닌 바이트 데이터로 변환.( 첨부파일 필수 )
        console.log( signUpFormData );


    console.log(id);console.log(pw);console.log(name);console.log(email);
    console.log(phone);
    console.log(img);


    // 2. 객체화 [ let info = { } ]
        // let info = {
        // id : id,
        // pw : pw,
        // name:name,
        // email:email,
        // phone:phone,
        // img:img
        // }


    // 3. 객체를 배열에 저장 --> spring controller 서버 와 통신 ( JQUERY AJAX )
    $.ajax({
            url : '/hiyoweb/signup',
            method : 'Post',
    //            data : info,
            data : signUpFormData, // form 보내기
            contentType : false,
            processData : false,
            success : function (result){
                console.log(result);
                // 4. 결과
                if(result){
                    alert('회원가입 성공');
                    location.href = '/hiyoweb/login';
                }else{
                    alert('회원가입 실패');
                }
            }
        })

    // 4. 결과
}

// 2. 로그인
function login(){
    console.log("로그인켜짐")
    // 1. HTML 입력값 호출 [document.querySelector() ]
        let id = document.querySelector('#id').value;
        let pw = document.querySelector('#pw').value;
        console.log(id);console.log(pw);

    // 2. 객체화 {}
    let info = {id:id  , pw : pw};
    console.log(info);

    // 3. 객체를 배열에 저장 --> spring controller 서버 와 통신 ( JQUERY AJAX )

    $.ajax({
       url : `/hiyoweb/login`,      // 어디에
       method : 'post',             // 어떻게
       data :  info  ,              // (무엇을)입력받은값 보내기
       success : function ( result ){
            console.log(result);

            if(result){
                alert("로그인성공");
                // JS 페이지 전환
                location.href = '/'; // 로그인 성공시 메인페이지로
            }
            else{alert("로그인실패")}
       }
    })//아작스 끝

}


function onChangeImg(event){
    console.log("이미지출력 함수 실행됨");
    console.log(event);
    console.log(event.files); // 현재 input의 첨부파일들
    console.log(event.files[0]); // 현재 input의 첨부파일들 중에서 첫번째 파일

    // 1. input에 업로드 된 파일을 바이트로 가져오기
        // new FileReader() : 파일 읽기 관련 메소드 제공
    // 1. 파일 읽기 객체 생성
    let fileReader = new FileReader();
    // 2. 파일 읽기 메소드
    fileReader.readAsDataURL( event.files[0] );
    console.log(fileReader);
    console.log(fileReader.result);

    // 3. 파일 onload 정의
    fileReader.onload = e =>{
        console.log(e);
        console.log(e.target);
        console.log(e.target.result); // 여기에 읽어온 첨부파일 바이트
        
        // 선택한 이미지 출력
        document.querySelector('#preimg').src = e.target.result;
    }
}// function end

/* 
    배열타입 , 함수타입 == 객체 타입
    함수 정의 방법
        1. function 함수명( 매개변수 ){ }
        2. function( 매개변수 ){ }

        3. (매개변수) => { }
*/
