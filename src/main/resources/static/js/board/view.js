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
                }// function2 end
            })// ajax 2 end
            let loginId = document.querySelector('.top_menu > #login_menu');
            console.log(loginId);
            onReplyList();
        }// function1 end   
    }); // ajax1 end
}// method end

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

// 4. 댓글작성
function onReplyWrite(brindex){

    $.ajax({
        url : "/board/reply/write.do",
        method : "post",
        data : {'bno':bno ,      // URL 주소에서 따와서 씀 (현재보고있는 게시물번호)
        'brcontent':document.querySelector(`.brcontent${brindex}`).value,
        'brindex':brindex
        },
        success : function(response){
            console.log("onReplyWrite()");
            if(response){alert("댓글 작성 성공!");}
            else{alert("댓글 작성 실패!");}
            
            onView();
            
            document.querySelector(`.brcontent${brindex}`).value = '';
            document.querySelector(`.brcontent${brindex}`).innerHTML = '';
        }

    });
}

// 4. 댓글출력 [ 1. 현제게시물 출력되었을때 2. 댓글작성시 3. 댓글삭제 4. 댓글수정 ]
function onReplyList(){
    $.ajax({
        url : "/board/reply/do",
            method : "get",
            data : {'bno':bno ,      // URL 주소에서 따와서 씀 (현재보고있는 게시물번호)
            },
            success : function(response){
                console.log(response);
                let replyListBox = document.querySelector('.replyListBox');
                let html = ``;

                response.forEach((reply) => {
                    html +=`<div>
                                <span>${reply.brcontent}</span>
                                <span>${reply.mno}</span>
                                <span>${reply.brdate}</span>
                                <button type="button" onclick="subReplyView(${reply.brno})"> 답변작성 </button>
                                <div class="subReply${reply.brno}"> </div>
                            ${onSubReplyList(reply.subReply)}
                            </div>`      // class 명 뒤에 식별키(pk) 연결
                    
                });// for1 end
                replyListBox.innerHTML = html;

            }//funtion end
        });//ajxa end
}// funtion end

// 5. 대댓글 작성칸 생성함수
function subReplyView(brno){
    let subReply = document.querySelector(`.subReply${brno}`);

    let html = `
                <textarea class="brcontent${brno}" name="" id=""></textarea>
                <button onclick="onReplyWrite(${brno})" type="button"> 답변작성 </button>
                `;

    subReply.innerHTML = html;

}

// 6. 대댓글 호출 함수
function onSubReplyList( subReply ){
    console.log(subReply);
    let subHTML = ``;
    subReply.forEach((result)=>{
        subHTML += `<div style="margin-left : 50px;">
                        <span>${result.brcontent}</span>
                        <span>${result.mno}</span>
                        <span>${result.brdate}</span>        
                    </div>`
    });
    return subHTML;
}