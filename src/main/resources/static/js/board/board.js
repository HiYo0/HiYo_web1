console.log("board.js실행됨");

// 페이지관련 객체 //
let pageObject = {
    page:1,              // 현재페이지수
    pageBoardSize:2,     // 페이지에 출력할 게시물수
    bcno:0,              // 현재 카테고리
    key:"b.btitle",      // 현재검색 key
    keyword:""           // 현재 검색keyword
}

// 1. 전체 출력용 : 함수 : 매개변수 X 반환 X 실행시기 : 페이지가 열릴떄(JS)
doViewList(1)
function doViewList( page ){
    console.log("doViewList() 실행됨");
    
    pageObject.page = page // 매개변수로 들어온 값을 전역변수에 입력받은 값 대입

    $.ajax({
        url: "/board/do",
        method : "get",
        data: pageObject,
        success: function (response) {
            console.log(response);
            // 테이블에 레코드 구성
            // 1. 어디에
            let boardTableBody = document.querySelector('#boardTableBody');
            // 2. 무엇을
            let html = ``;
                // 서버가 보내준 데이터를 출력
                // 방법 1. 
                response.list.forEach(board => {
                    console.log(board);
                    html += `<tr>
                                <th>${board.bno}</th>
                                <td><a href="/board/view?bno=${board.bno}">${board.btitle}</a></td>
                                <td>
                                    <img src="/img/${board.mimg}"
                                    style="width:20px; border-radius:50%;"/>${board.mid}
                                </td>
                                <td>${board.bdate}</td>
                                <td>${board.bview}</td>
                            </tr>`;
                });
                // 방법 2.
//                for(let i = 0; i<response.length; i++){
//                    console.log(response[i]);
//                }
            // 3. 출력
            boardTableBody.innerHTML = html;

            // ==페이지네이션 구성======================================= //////
            // 1. 어디에
            let pagination = document.querySelector('.pagination');
            // 2. 무엇을
            let pagehtml = ``;
                // 이전버튼 ( 만약 현재페이지가 첫 페이지이면 1페이지 고정)
                pagehtml += `<li class="page-item"><a class="page-link" onclick="doViewList(${page-1<1? 1 :page-1})">이전</a></li>`;

                // 페이지버튼 ( 막약 i가 현재페이지와 같으면 active 클래스 삽입 아니면 생략)
                for(let i = response.startBtn ; i <=response.endBtn; i++){
                    pagehtml +=`<li class="page-item"><a class="page-link ${page == i?'active':''}" onclick="doViewList(${i})">${i}</a></li>`;
                }

                // 다음 버튼 ( 만약 현재페이지가 마지막 페이지이면 현재페이지 고정)
                pagehtml +=`<li class="page-item"><a class="page-link" onclick="doViewList(${page+1>response.totalPage?response.totalPage:page+1})">다음</a></li>`;
            // 3. 출력
            pagination.innerHTML = pagehtml;

            // == 3. 부가 출력 =========================================================
            document.querySelector('.totalPage').innerHTML = response.totalPage;
            document.querySelector('.totalBoardSize').innerHTML = response.totalBoardSize;


            document.querySelector('.keyword').value = '';// 검색 입력어 지우기
        }
    });//ajax end
    return;

}


// 2. 페이지당 게시물수 selct box
function onPageBoardSize( object ){
    console.log("onPageBoardSize() 실행됨");
    console.log(object.value);
    pageObject.pageBoardSize = object.value; // 전역변수 값을 입력받은 값으로 변경함
    doViewList(1); // 1페이지로 새로고침
}

// 3. 카테고리
function onbcno(bcno){
    console.log("onbcno() 실행됨");
    console.log("선택된 bcno : "+bcno);

    // bcno :  카테고리 식별번로 [ 0 : 전체 1 : 자유 2 : 노하우 ]
    pageObject.bcno =  bcno;

    // 입력한 검색어 초기화
            // document.querySelector('.key').value = 'b.btitle';
            document.querySelector('.keyword').value = '';
            pageObject.keyword = '';

            // 카테고리 활성화 CSS적용
            // 1. 모든카테고리 호출
            let categoryFont = document.querySelectorAll('#categoryFont > a');
            console.log(categoryFont);
            // .classList.add("클래스명") 추가
            // .classList.remove("클래스명") 삭제
            
            for(let i = 0 ; i<categoryFont.length; i++){
                categoryFont[i].classList.remove("clickCategory")
            }
            categoryFont[bcno].classList.add("clickCategory");


    doViewList(1);// 1페이지로 새로고침
}

// 4. 검색 함수
function onSearch(){
    // 1. 입력받은 값 가져오기
    let key = document.querySelector('.key').value;
    let keyword = document.querySelector('.keyword').value;

    // 2. 서버에 전송할 객체에 담아주고
    pageObject.key = key;
    pageObject.keyword = keyword;

    // 3. 출력
    doViewList(1);// 1페이지로 새로고침
    
}