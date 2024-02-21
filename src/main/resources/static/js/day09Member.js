
console.log('day09Member.js');


function insert2(){
    console.log("insert() 실행");

    let mname = document.querySelector('#mname').value;    console.log( mname );
    let mphone = document.querySelector('#mphone').value;     console.log( mphone );

    let info = { mname : mname, mphone : mphone };  console.log(info);

    $.ajax({
           url : '/member/insert',  method : 'post', data : info ,
           success : function ( result ){ console.log(result);
                // 4. 결과
                if( result ){  alert('사원등록 성공'); read();}
                else{    alert('사원등록 실패');    }
           }
    }) // ajax end

}

read();
function read(){
    console.log("read() 실행");

    $.ajax({
           url : '/member/read',
           method : 'GET',
           success : function ( result ){ console.log(result);
                // 1. 어디에
                let tbody = document.querySelector('#table tbody');
                // 2. 무엇을
                let html = "";
                for( let i = 0 ; i<result.length ; i++ ){
                    console.log( result[i] );
                    // 백틱 ` : 키보드 tab키 위에 있는 키 // ``백틱 문자열 사이에 ${ JS코드 } 대입하는 템플릿
                    html += `<tr>
                                 <td> ${ result[i].mno }  </td>
                                 <td> ${ result[i].mname }  </td>
                                 <td> ${ result[i].mphone }  </td>
                                 <td>
                                     <button onclick="doDelete( ${ result[i].mno }  )" >삭제</button>
                                     <button onclick="update( ${ result[i].mno }  )" >수정</button>
                                     <button onclick="salaryView( ${ result[i].mno} )">급여지급</button>
                                 </td>
                             </tr>`
                };
                // 3. 출력
                tbody.innerHTML = html;
                htmlDelete();
           }
    })
}

function update( mno ){
    console.log("update() 실행");
    // 1. 수정할 내용 가져온다.
    let mphone = prompt('전화번호 수정 ');
    // 2. 객체화
    let info = {
        mno: mno ,
        //mname : mname,
        mphone : mphone
    };
    // ----------- AJAX ------------- //
    $.ajax({
       url : '/member/update',
       method : 'POST',
       data : info  ,
       success : function ( result ){
            if( result ){ alert('사원연락처 수정 성공'); read(); htmlDelete();}
            else{ alert('사원연락처 수정 실패'); }

       }
    })
}

function doDelete( mno ){
    console.log("delete() 실행")

    $.ajax({
           url : '/member/delete',
           method : 'GET',
           data:{mno : mno},
           success : function ( result ){
                if( result ){ alert('삭제 성공'); read(); htmlDelete();}
                else{ alert('삭제 실패'); }
           }
    })
}


function salaryView( mno ){
    console.log("salaryRead() 실행");

    let tbody = document.querySelector('#salary_main');
                        // 2. 무엇을
    let html = "";

    // 백틱 ` : 키보드 tab키 위에 있는 키 // ``백틱 문자열 사이에 ${ JS코드 } 대입하는 템플릿
    html += `<h3> 급여관리 </h3>
                     내역 : <input id="scontent" value=""/>
                     금액 : <input id="salary" value=""/>
                     <button onclick="salaryInsert(${mno})"> 등록 </button>

                     <table id="salary_table" style="width:100%; border:1px solid black; margin:20px; padding:5px; text-align:center" >
                         <thead>
                         <tr>
                             <th> 내역 </th> <th> 금액 </th>
                         </tr>
                         </thead>
                         <tbody>

                         </tbody>
                     </table>`
                        // 3. 출력
    tbody.innerHTML = html;

    salaryRead(mno);
}

function salaryInsert( mno ){
    console.log("salaryInsert() 실행");

        let scontent = document.querySelector('#scontent').value;    console.log( scontent );
        let salary = document.querySelector('#salary').value;     console.log( salary );

        let info = { mno:mno , scontent : scontent, salary : salary };  console.log(info);

        $.ajax({
               url : '/salary/insert',  method : 'post', data : info ,
               success : function ( result ){ console.log(result);
                    // 4. 결과
                    if( result ){  alert('급여지급 성공'); salaryRead( mno );}
                    else{    alert('급여지급 실패');    }
               }
        }) // ajax end

}

function salaryRead( mno ){
    console.log("salaryRead() 실행");

        $.ajax({
               url : '/salary/read',
               method : 'GET',
               data:{mno : mno},
               success : function ( result ){ console.log(result);
                    // 1. 어디에
                    let tbody = document.querySelector('#salary_table tbody');
                    // 2. 무엇을
                    let html = "";
                    for( let i = 0 ; i<result.length ; i++ ){
                        console.log( result[i] );
                        // 백틱 ` : 키보드 tab키 위에 있는 키 // ``백틱 문자열 사이에 ${ JS코드 } 대입하는 템플릿
                        html += `<tr>
                                     <td> ${ result[i].scontent }  </td>
                                     <td> ${ result[i].salary }  </td>
                                 </tr>`
                    };
                    // 3. 출력
                    tbody.innerHTML = html;
               }
        })
}

function htmlDelete(){
    let tbody = document.querySelector('#salary_main');
    tbody.innerHTML = "";
}