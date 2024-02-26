console.log("index JS 실행됨");

// 모든페이지에서 적용할 공통 JS

// 1. 로그인 여부 확인 요청
$.ajax({
    url : '/hiyoweb/login/check',
    method : 'get',
    success : function(result){
        console.log(result);

        // 1. 어디에
        let login_menu = document.querySelector('#login_menu');
        let html = ``;
        if(result != ''){//로그인 했을때

            // 2. 무었을
             html = `<li class="nav-item">   <a class="nav-link" onclick="logout()">로그아웃</a></li>
                        <li class="nav-item">   <a class="nav-link" href="#">마이페이지</a></li>
                        <li class="nav-item"> <img src='#'/> ${result} 님</li>
                        `;

        }else{// 로그인 안했을때
             html = `<li class="nav-item">   <a class="nav-link" href="/hiyoweb/login">로그인</a></li>
                        <li class="nav-item">   <a class="nav-link" href="/hiyoweb/signup">회원가입</a></li>
                        `;
        }
        // 3. 대입
        login_menu.innerHTML = html;
    }
})//ajax end

// 2. 로그아웃
function logout(){
    $.ajax({
        url : `/hiyoweb/logout`,
        method : "get",
        success : function(result){
            if(result){
            alert('로그아웃 했습니다.');
                location.href = '/';
            }else{alert('로그아웃 실패[관리자에게 문의]');}

        }
    })
}