console.log("todo.js실행")

// 1. 할일 등록함수
function doPost(){}
// 2. 할일 출력함수
doGet(); // JS 실행시 최초로 1번 실행
function doGet(){
    // - 스프링(자바) 와 통신( 주고 받고 )
    // JQUERY AJAX
        // $.ajax( JSON 형식의 통신정보 )
        //$.ajax({})
        /*
            HTTP method : post , get , put , delete 등등

            $.ajax({})
            $.ajax({
                url : 'spring controller url / 통신 대상 식별',
                method : 'HTTP method / 통신방법',
                data : 'HTTP request value / 통신 요청으로 보낼 데이터'
                success : HTTP request function / 통신 응답 함수
            })
        */

    $.ajax({
        url : '/todo/get.do',
        method : 'get',
        success : function result( resultValue ){
            console.log(resultValue);
            // 통신 응답 결과를 HTML 형식으로 출력해주기.
            // 1. 어디에
            let tbody = document.querySelector('table tbody')
            // 2. 무엇을
            let html = ``;
                for(let i = 0; i<resultValue.length ; i++){
                    html += `<tr>
                                 <th>${resultValue[i].id}</th>
                                 <th>${resultValue[i].content}</th>
                                 <th>${resultValue[i].deadline}</th>
                                 <th>${resultValue[i].state}</th>
                             </tr>`
                }// for end
            // 3. 대입
            tbody.innerHTML = html;
        }// success end
    })//ajax end
}// method end
// 3. 할일 상태 수정함수
function doPut(){}
// 4. 할일 삭제함수
function doDelete(){}