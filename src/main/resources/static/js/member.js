console.log('member.js')

// 1. 회원가입
function signup(){
    // 1. HTML 입력값 호출 [document.querySelector() ]
    // 방법1. 데이터 하나씩 요청
        // let id = document.querySelector('#id').value;
        // let pw = document.querySelector('#pw').value;
        // let name = document.querySelector('#name').value;
        // let email =document.querySelector('#email').value;
        // let phone = document.querySelector('#phone').value;
        // let img = document.querySelector('#img').value;
    // 방법2. 폼 가져오기
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

/*
    onclick
    onchange
*/
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
