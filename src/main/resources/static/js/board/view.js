console.log("view.js()실행")

// http://localhost/board/view?bno=3&mno=4&type=자유

// * JS에서 경로(URL)상의 쿼리스트링(매개변수) 호출하기
    // 1. new URL(lovation.href) : 현재 페이지의 경로호출
console.log( new URL(location.href) );
    // 2. 경로상의 (쿼리스트링) 매개변수들
console.log( new URL(location.href).searchParams );
    // 3. (쿼리스트링)매개변수들 에서 특정 매개변수 호출
console.log( new URL(location.href).searchParams.get('bno') );
    
let bno = new URL( location.href ).searchParams.get('bno'); // URL에서 때옴

onView();
// 1. 개시물 개별 조회
function onView(){
    console.log("onView()실행됨")
    $.ajax({
        url : "/board/view.do",
        method : "get",
        data : { "bno" : bno },
        success : function(result){
            console.log(result);
            
            document.querySelector('.btitle').innerHTML = result.btitle;
            document.querySelector('.bcontent').innerHTML = result.bcontent;
            document.querySelector('.bcno').innerHTML = result.bcno==1?'자유':result.bcno==2?'노하우':'';
            document.querySelector('.mno').innerHTML = result.mid;
            document.querySelector('.bdate').innerHTML = result.bdate;
            document.querySelector('.bview').innerHTML = result.bview;

            //* 다운로드 링크
            document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${ result.bfile }"> ${ result.bfile } </a>`;

            //* 삭제 / 수정 버튼 활성화 ( 해당 보고있는 클라이언트가 작성자의 아이디와 동일하면 )
                // 유효성 검사
                // 현재 로그인된 아이디 또는 번호 ( 1. 헤더 HTML 가져온다. 2.서버에게 요청)
                $.ajax({
                    url : "/hiyoweb/login/check",
                    method : "get",
                    success : function (response2){
                        console.log(response2);
                        if(response2 == result.mid){ // 현재 로그인한 ID와 작성자가 같으면 버튼출력
                            let btnHTML = `<button type="button" onclick="onDelete()">삭제</button>`;
                            btnHTML += `<button type="button" onclick="location.href='/board/update?bno=${result.bno}'">수정</button>`;
                            btnHTML += `<button type="button" onclick="location.href='/board/'"> 목록보기 </button>`;
                            document.querySelector('.btnBox').innerHTML = btnHTML;
                        }
                    }
                })
                let loginId = document.querySelector('.top_menu > #login_menu');
                console.log(loginId);

        }
    });
}

// 2. 개시물 삭제 함수
function onDelete(){
    $.ajax({
        url : "/board/delete.do",
        method : "delete",
        data : {'bno':bno}, // URL 주소에서 따와서 씀
        success : function(response){
            if(response){
                alert('삭재성공');
                location.href = '/board/';
            }else{alert('삭재실패');}
        }
    });
}

// 3. 게시물 수정 함수
function onUpdate(){
    $.ajax({
            url : "/board//update.do",
            method : "put",
            data : {'bno':bno}, // URL 주소에서 따와서 씀
            success : function(response){
                if(response){
                    alert('삭재성공');
                    location.href = '/board/';
                }else{alert('삭재실패');}
            }
        });
}