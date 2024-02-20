console.log("실행됨")


// 1. 저장 : 실행조건 : 등록버튼 클릭시 매개변수X ,리턴X
function docreate(){
    console.log("저장 실행됨")

    $.ajax({
       url : '_2인과제2/create',
       method : 'Post',
       data :   ,
       success : function ( 서버 응답 받은 매개변수 ){ }
    })
}

// 2. 호출 : 실행조건 : 페이지 열릴때, 변화(저장,수정,삭제)가 있을때.   매개변수X ,리턴X
deread(); // 스크립트 열릴때
function deread(){
    console.log("호출 실행됨")
}

// 3. 수정 : 실행조건 : 수정버튼 클릭시  매개변수 : 수정할식별키 ,리턴X
function doupdate(bno){
    console.log("수정 실행됨")
}

// 4. 삭제 : 실행조건 : 삭제버튼 클릭시  매개변수 : 삭제할식별키 ,리턴X
function dedelete(bno){
    console.log("삭제 실행됨")
}