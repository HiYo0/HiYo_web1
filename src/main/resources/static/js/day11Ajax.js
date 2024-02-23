console.log("아작스JS 실행됨");

    // 1. 함수 : function 함수명(){ }
    // 2. 익명 : function (){}
    // 3. (익명)화살표 : ()=>{}

// 테스트용 변수
    let id = 9;
    let content = "AJAX테스트중";
// 1. 간단한 통신
function ajax1(){

    console.log("ajax1 켜짐");
    $.ajax({
        url : "/day11/ajax1",
        method : "get",
        success : ( result ) => {console.log(result);},
        error : ( error ) => {console.log(error);}
    });
} //function End

// 2. 경로상에 매개변수 포함하기.
function ajax2(){
    console.log("ajax2 켜짐");
        $.ajax({
            url : `/day11/ajax2/${id}/${content}`,
            method : "get",
            success : ( result ) => {console.log(result);}
        });
}//function end

// 3. 경로상에 쿼리스트링 포함하기
function ajax3(){
    console.log("ajax3 켜짐");
        $.ajax({
            url : `/day11/ajax3?id=${id}&content=${content}`,
            method : "get",
            success : ( result ) => {console.log(result);}
        });
}//function end

// 4. HTTP 본문(body) 에 객체 보내기
function ajax4(){
    console.log("ajax4 켜짐");
        $.ajax({
            url : '/day11/ajax4',
            method : "get",
            data : {id :id , content :content},
            success : ( result ) => {console.log(result);}
        });
}//function end

// 5. body(본문)에 데이터 보내는 방식 contentType : from
function ajax5(){
    console.log("ajax5 켜짐");
        $.ajax({
            url : '/day11/ajax5',
            method : "post",
            data : {id :id , content :content},
            success : ( result ) => {console.log(result);}
        });
}//function end

// 6. body(본문)에 데이터 보내는 방식 contentType : JSON
function ajax6(){
    console.log("ajax6 켜짐");
        $.ajax({
            url : '/day11/ajax6',
            method : "post",
            data : JSON.stringify({id :id , content :content}),
            contentType : "application/json",
            success : ( result ) => {
                console.log(result);
            }
        });
}//function end