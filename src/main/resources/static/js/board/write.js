// 썸머노트 실행
$(document).ready(function() {

    // 썸머노트 옵션객체
    let option = {
        lang : 'ko-KR', // 한글패치
        height : 500    // 에디터 세로 크기
    }

    $('#summernote').summernote( option );
});

// 1. 글쓰기

function onWrite(){
    console.log("onWire()실행됨")    
    // 1. 폼DOM 가져오기
    let boardWriteForm = document.querySelector('.boardWriteForm');

    // 2. 폼 바이트(바이너리) 객체 변환[ 첨부파일 보낼때는 필수 ]
    let boardWriteFormData = new FormData( boardWriteForm );
    console.log(boardWriteFormData);
    
    // 3. ajax 첨부파일 폼 전송
    $.ajax({
        url : "/board/write.do",
        method : "post",
        data: boardWriteFormData ,
        contentType: false,
        processData : false,
        success: function (response) {
            console.log(response);
            if(response == 0){
                alert('글쓰기 실패 : 관리자에게 문의(DB오류)');
            }else if(response==-1){
                alert('글쓰기 실패 : 관리자에게 문의(첨부파일 오류)');
            }else if(response ==-2){
                alert('로그인 세션이 존재하지 않습니다.(잘못된접근)');
                location.href = '/board/view?bno='+response;
            }else if(response>=1){
                alert('글쓰기 성공');
                location.href='/board/view?bno='+response;
            }

        }
    });//ajax end


}//function 글쓰기 End