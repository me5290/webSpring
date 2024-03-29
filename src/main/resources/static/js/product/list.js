// 클라이언트(브라우저) 위치 가져오기
navigator.geolocation.getCurrentPosition((myLocation)=>{
    console.log(myLocation);

    kakaoMapApi(myLocation);
});

function kakaoMapApi(myLocation){
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(myLocation.coords.latitude, myLocation.coords.longitude), // 지도의 중심좌표
        level : 4 // 지도의 확대 레벨
    });

    var imageSrc = '/img/mapicon.png', // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(54, 69), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
        markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다

    // 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 6 // 클러스터 할 최소 지도 레벨
    });

    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("/product/list.do", (response)=> {
        console.log(response);
        let markers = response.map((data)=>{
            // 1. 마커 생성
            let marker = new kakao.maps.Marker({
                position : new kakao.maps.LatLng(data.plat, data.plng),
                image : markerImage
            })

            // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function() {
                // 마커 위에 인포윈도우를 표시합니다
                // 마커 클릭시 사이드바 열기
                document.querySelector('.sideBarBtn').click();

                // 사이드바 내용물
                    // 1. 제품 제목
                document.querySelector('.offcanvas-title').innerHTML = `제품명 : ${data.pname} , 가격 : ${data.pprice}`

                    // 2. 제품 이미지들
                let carouselHTML = ``;

                let imgindex = 0;
                data.pimg.forEach((img)=>{
                    carouselHTML += `
                        <div class="carousel-item ${imgindex == 0 ? 'active' : ''}">
                            <img style="height:200px; object-fit:contain;" src="/img/${img}" class="d-block w-100" alt="...">
                        </div>
                    `
                    imgindex++;
                })

                document.querySelector('.carousel-inner').innerHTML = carouselHTML;

                    // 3. 제품 가격/내용들

                    // 4. 버튼 (찜하기 , 채팅하기)
                plikeState(data.pno);
            });
            return marker; // 2. 클러스터 저장하기 위해 반복문 밖으로 생성된 마커 반환
        })

        // 3. 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
    });
}

// 2.
function plikeDo(pno , method){
    let result = false;
    $.ajax({
        url:'/product/plike.do',
        method:method,
        data:{pno:pno},
        async:false,
        success:(r)=>{
            console.log(r);
            result = r;
        }
    });
    if(method != 'get'){ // 순환 참조 해결 후 get실행
        plikeState(pno);
    }
    return result;
}

// 3. 찜하기 상태 출력 함수 // 실행 될때 : 사이드바 열릴때 , 찜하기 변화 있을때
function plikeState(pno){
    // 현재 로그인 했고 찜하기 상태 여부 따라 css 변화
    let result = plikeDo(pno,'get');
    if(result){ // 로그인 했고 이미 찜하기 상태
        document.querySelector('.sideBarBtnBox').innerHTML = `
            <button type="button" onclick="plikeDo(${pno},'delete')">찜하기 ★</button>
            <button type="button">채팅하기</button>
        `;
    }else{ // 로그인 안했거나 찜하기 안한 상태
        document.querySelector('.sideBarBtnBox').innerHTML = `
            <button type="button" onclick="plikeDo(${pno},'post')">찜하기 ☆</button>
            <button type="button">채팅하기</button>
        `;
    }
}