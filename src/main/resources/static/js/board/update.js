console.log("update.js 실행됨");
// 썸머노트 실행 ($: jquery 문법 )
    //$(document).ready(function() {} : 문서(HTML)이 모두 렌더링 되었을떄




// http://localhost/board/view?bno=3&mno=4&type=자유

// * JS에서 경로(URL)상의 쿼리스트링(매개변수) 호출하기
    // 1. new URL(lovation.href) : 현재 페이지의 경로호출
console.log( new URL(location.href) );
    // 2. 경로상의 (쿼리스트링) 매개변수들
console.log( new URL(location.href).searchParams );
    // 3. (쿼리스트링)매개변수들 에서 특정 매개변수 호출
console.log( new URL(location.href).searchParams.get('bno') );

let bno = new URL( location.href ).searchParams.get('bno'); // URL에서 때옴


// 1. 개시물 개별 조회
onView();
function onView(){
    console.log("onView()실행됨")

    $.ajax({
        url : "/board/view.do",
        method : "get",
        data : { "bno" : bno },
        success : function(result){
            console.log(result);

            document.querySelector('.btitle').value = result.btitle;
            document.querySelector('.bcontent').innerHTML = result.bcontent;
            document.querySelector('.bcno').value = result.bcno;
            document.querySelector('.bfile').innerHTML = result.bfile;

            // 썸머노트 옵션객체
            let option = {
                lang : 'ko-KR', // 한글패치
                height : 500    // 에디터 세로 크기
            }
            $('#summernote').summernote( option );
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

    // 1. 폼 가져온다.
    let boardUpdateForm = document.querySelector('.boardUpdateForm');
    // 2. 폼 객체화 ( 첨부파일 바이트화 )
    let boardUpdateFormData = new FormData( boardUpdateForm );
    console.log(boardUpdateFormData);

        // + 폼 객체에 데이터 추가. [ HTML 입력 폼 외 데이터 삽입 가능 ]
        // 폼데이터객체명.set( 속성명(name) , 데이터(value) )
        boardUpdateFormData.set('bno',bno);

    $.ajax({
            url : "/board/update.do",
            method : "put",
            data : boardUpdateFormData, // URL 주소에서 따와서 씀
            contentType : false,
            processData : false,
            success : function(response){
                if(response){
                    alert('수정성공');
                    location.href = '/board/view?bno='+bno;
                }else{alert('수정실패');}
            }
        });
}
