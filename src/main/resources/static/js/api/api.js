// 카카오 지도 api
    // 1. 카카오 지도 객체
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(37.3218778, 126.8308848), // 지도의 중심좌표
        level : 4 // 지도의 확대 레벨
    });

    // 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 8 // 클러스터 할 최소 지도 레벨
    });

    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.ajax({
        url:'https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=618&serviceKey=Jr6Y4gNiQyL5XgE4jEXJ%2BTNBDXYoiahic6dM9fvz4mfYcshcsH%2B9P%2FSr431QpY1OvPDaLe5kf87h%2B%2BLSm7Fp7Q%3D%3D',
        method:'get',
        success:(response)=>{
            console.log(response);
            var markers = response.data.map((object)=> {
                return new kakao.maps.Marker({
                    position : new kakao.maps.LatLng(object.식당위도, object.식당경도)
                });
            });

            // 클러스터러에 마커들을 추가합니다
            clusterer.addMarkers(markers);
        }
    })
//    $.get("https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=618&serviceKey=Jr6Y4gNiQyL5XgE4jEXJ%2BTNBDXYoiahic6dM9fvz4mfYcshcsH%2B9P%2FSr431QpY1OvPDaLe5kf87h%2B%2BLSm7Fp7Q%3D%3D", function(r) {
//        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
//        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
//        var markers = r.data.map((object)=> {
//            return new kakao.maps.Marker({
//                position : new kakao.maps.LatLng(object.식당위도, object.식당경도)
//            });
//        });
//
//        // 클러스터러에 마커들을 추가합니다
//        clusterer.addMarkers(markers);
//    });

// 안산시 원곡동 일반음식점 api
$.ajax({
    url:'https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=618&serviceKey=Jr6Y4gNiQyL5XgE4jEXJ%2BTNBDXYoiahic6dM9fvz4mfYcshcsH%2B9P%2FSr431QpY1OvPDaLe5kf87h%2B%2BLSm7Fp7Q%3D%3D',
    method:'get',
    success:(response)=>{
        console.log(response);

        let apiTable2 = document.querySelector('.apiTable2');
        let html = ``;

        response.data.forEach((ob)=>{
            html += `
                <tr>
                    <td>${ob.사업장명}</td>
                    <td>${ob.도로명전체주소}</td>
                    <td>${ob.대표메뉴1}</td>
                    <td>${ob.대표전화}</td>
                    <td>${ob.영업시간}</td>
                </tr>
            `;
        })

        apiTable2.innerHTML = html;
    }
})

// 안산시 강우량 api
$.ajax({
    url:'https://api.odcloud.kr/api/15111852/v1/uddi:71ee8321-fea5-4818-ade4-9425e0439096?page=1&perPage=100&serviceKey=Jr6Y4gNiQyL5XgE4jEXJ%2BTNBDXYoiahic6dM9fvz4mfYcshcsH%2B9P%2FSr431QpY1OvPDaLe5kf87h%2B%2BLSm7Fp7Q%3D%3D',
    method:'get',
    success:(r)=>{
        console.log(r);

        let apiTable1 = document.querySelector('.apiTable1');
        let html = ``;

        r.data.forEach((ob)=>{
            html += `
                <tr>
                    <td>${ob.관리기관명}</td>
                    <td>${ob.날짜}</td>
                    <td>${ob.시도명} ${ob.시군구명} ${ob.읍면동}</td>
                    <td>${ob['우량(mm)']}</td>
                </tr>
            `;
        })

        apiTable1.innerHTML = html;
    }
})