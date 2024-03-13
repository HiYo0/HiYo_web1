// 내(클라이언트 / 브라우저 사용자 ) 위치 가져오기
    // 1. navigator.geolocation.getCurrentPosition() : 현재 위치 정보 호출(JS)
navigator.geolocation.getCurrentPosition(( myLocation )=>{
    console.log( myLocation );
    console.log( '내 위도 = '+myLocation.coords.latitude ) ; // 현재위치 위도 lat
    console.log( '내 경도 = '+myLocation.coords.longitude ); // 현재 위치 경도 lng

    // 카카오 지도 실행
    kakaoMapView( myLocation.coords.latitude , myLocation.coords.longitude )// 매개변수 : 내 위도,경도
});

function kakaoMapView(latitude,longitude){
    // 1. 지도객체
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표 
        level : 3 // 지도의 확대 레벨 
    });
    // ======================= 마커이미지 ========================== //
    var imageSrc = '/img/mapicon.png', // 마커이미지의 주소입니다    
        imageSize = new kakao.maps.Size(44, 49), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
        
    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
        markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다
    // ======================= 마커이미지 ========================== //

    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 4 // 클러스터 할 최소 지도 레벨 
    });

    // 3. 마커 생성후 크럴스터 넣을 마커들의 데이터
        // 데이터를 가져오기 위해 jQuery를 사용합니다
        // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("/product/list.do", function(response) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        let markers = response.map((data) => {
            console.log(data);
            // 1. 마커 생성
            let marker = new kakao.maps.Marker({ 
                position : new kakao.maps.LatLng(data.plet , data.plng),
                image : markerImage
            });

            // - 클러스터에 넣기전에 마커 커스텀
            // 1. 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function() {
                alert(`제품명 : ${data.pname} 가격 : ${data.pprice} `);
                
                // 2. 만약에 마커 클릭시 사이드 바 열기
                document.querySelector('.sideBarBtn').click();
                // 3. 사이드바 내용물
                    // 1. 제품 제목
                document.querySelector('.offcanvas-title').innerHTML = `제품명 : ${data.pname}`;
                    // 2. 제품 이미지들
                let caruselHTML =``;
                let index = 0;
                data.pimg.forEach((img) => {
                    caruselHTML += `<div class="carousel-item ${ index == 0 ? 'active':''}">
                                        <img style="height : 300px; object-fit:contain" src="/img/${img}" class="d-block w-100" alt="...">
                                    </div>`;
                    index ++;
                });

                document.querySelector('.offcanvas-body .carousel-inner').innerHTML = caruselHTML;
                // 3. 제품 가격/내용들

                    
                // 4. 버튼( 찜하기 , 채팅하기 )
                plikeView(data.pno);
               
                    

            });
            return marker; // 클러스터 저장하기 위해 반복문 밖으로 생성된 마커 반환
        });
        
        // 3. 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    });

}

// plikeWrite(3,'get');
// plikeWrite(3,'post');
// plikeWrite(3,'delete');
// 2.
function plikeWrite(pno , method){
    let result = false;
    $.ajax({
        url: "/product/plike.do",
        method : method,
        data: {"pno":pno},
        async:false,
        success: function (response) {
            console.log(response);
            result = response;
        }
    });
    if(method!='get'){plikeView(pno);}// 찜하기 변화가 있을때
    return result;
}
// 3. 찜하기 상태 출력함수 // 1. 사이드바 열릴떄 2. 찜하기 변화가 있을때.
function plikeView(pno){
    // *현재 로그인 했고 찜하기 상태여부 따라 CSS변화
    let result = plikeWrite(pno , 'get');
    if (result){ // 로그인 했고 이미 찜하기를 했는경우
        document.querySelector('.offcanvas-body .productSideBarBtnBox').innerHTML = `
            <button type="button" onclick="plikeWrite(${pno},'delete')"> 찜하기 ★ </button>
            <button type="button"> 채팅하기 </button>
            `
     }else{
        document.querySelector('.offcanvas-body .productSideBarBtnBox').innerHTML = `
            <button type="button" onclick="plikeWrite(${pno},'post')"> 찜하기 ☆ </button>
            <button type="button"> 채팅하기 </button>
            `;
    }
     
}