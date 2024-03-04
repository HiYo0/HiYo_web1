// http://localhost/board/view?bno=3&mno=4&type=자유

// * JS에서 경로(URL)상의 쿼리스트링(매개변수) 호출하기
    // 1. new URL(lovation.href) : 현재 페이지의 경로호출
console.log( new URL(location.href) );
    // 2. 경로상의 (쿼리스트링) 매개변수들
console.log( new URL(location.href).searchParams );
    // 3. (쿼리스트링)매개변수들 에서 특정 매개변수 호출
console.log( new URL(location.href).searchParams.get('bno') );
    
let bno = new URL( location.href).searchParams.get('bno');
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
            document.querySelector('.mno').innerHTML = result.mno;
            document.querySelector('.bdate').innerHTML = result.bdate;
            document.querySelector('.bview').innerHTML = result.bview;

        }
    });
}