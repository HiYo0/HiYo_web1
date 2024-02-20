console.log("스크립트 실행됨")


// 1. 저장 : 실행조건 : 등록버튼 클릭시 매개변수X ,리턴X
function docreate(){
    console.log("저장 실행됨")
    let bcontent = document.querySelector('#bcontent').value;
    let bwriter = document.querySelector('#bwriter').value;
    let bpassword = document.querySelector('#bpassword').value;

    // 객체만들기
    let member = { bcontent:bcontent , bwriter:bwriter , bpassword:bpassword };

    $.ajax({
       url : '/_2인과제2/create',
       method : 'Post',
       data : member,
       success : function ( list ){
       if(list){alert('회원가입 성공');}
       else{alert('회원가입 실패');}

       }
    })
    doread();
}

// 2. 호출 : 실행조건 : 페이지 열릴때, 변화(저장,수정,삭제)가 있을때.   매개변수X ,리턴X
doread(); // 스크립트 열릴때
function doread(){
    console.log("호출 실행됨")
    $.ajax({
       url : '/_2인과제2/read',
       method : 'Get',
       success : function ( list ){
            console.log(list);
           let html = ``; // ` 백틱
           let tbody = document.querySelector('table tbody')
           for( let i = 0 ; i < list.length ; i++ ){
               html += `<tr>
                            <th> ${ list[i].bno } </th>
                            <th> ${ list[i].bcontent} </th>
                            <th> ${ list[i].bwriter}</th>
                            <th> ${ list[i].bpassword}</th>
                            <th>
                                <button type="button" onclick="doupdate(${ list[i].bno })">수정</button>
                                <button type="button" onclick="dodelete(${ list[i].bno })">삭제</button>
                            </th>
                        </tr>`
           } // for end
           // 3. 대입
           tbody.innerHTML = html;
       }
    })
}

// 3. 수정 : 실행조건 : 수정버튼 클릭시  매개변수 : 수정할식별키 ,리턴X
function doupdate(bno){
    console.log("수정 실행됨")
}

// 4. 삭제 : 실행조건 : 삭제버튼 클릭시  매개변수 : 삭제할식별키 ,리턴X
function dodelete(bno){
    console.log("삭제 실행됨")
}