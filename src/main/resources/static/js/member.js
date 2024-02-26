console.log('member.js')

// 1. 회원가입
function signup(){
    // 1. HTML 입력값 호출 [document.querySelector() ]
    let id = document.querySelector('#id').value;
    let pw = document.querySelector('#pw').value;
    let name = document.querySelector('#name').value;
    let email =document.querySelector('#email').value;
    let phone = document.querySelector('#phone').value;
    let img = document.querySelector('#img').value;
    console.log(id);console.log(pw);console.log(name);console.log(email);
    console.log(phone);
    console.log(img);

    // 2. 객체화 [ let info = { } ]
    let info = {
    id : id,
    pw : pw,
    name:name,
    email:email,
    phone:phone,
    img:img
    }


    // 3. 객체를 배열에 저장 --> spring controller 서버 와 통신 ( JQUERY AJAX )
    $.ajax({
            url : '/hiyoweb/signup',
            method : 'Post',
            data : info,
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