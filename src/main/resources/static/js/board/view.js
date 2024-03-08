

// * 경로(URL)상의 쿼리스트링(매개변수) 호출하기
    // 1. new URL(location.href) : 현재 페이지의 경로호출
console.log(new URL(location.href));
    // 2. [.searchParams] 경로상의 (쿼리스트링)매개변수들
console.log(new URL(location.href).searchParams);
    // 3. [.get('queryStringKey')] (쿼리스트링)매개변수들에서 특정 매개변수 호출
console.log(new URL(location.href).searchParams.get('bno'));

let bno = new URL(location.href).searchParams.get('bno');

// 1. 게시물 개별 조회
onView();
function onView(){
    console.log('onView()');
    $.ajax({
        url:"/board/view.do",
        method:"get",
        data:{'bno' : bno},
        success:(r)=>{
            console.log(r);
            let category = "";
            if(r.bcno == 1){
                category = "자유";
            }else if(r.bcno == 2){
                category = "노하우";
            }
            document.querySelector('.bcno').innerHTML = category;
            document.querySelector('.btitle').innerHTML = r.btitle;
            document.querySelector('.bcontent').innerHTML = r.bcontent;
            document.querySelector('.mno').innerHTML = r.mid;
            document.querySelector('.bdate').innerHTML = r.bdate;
            document.querySelector('.bview').innerHTML = r.bview;
            // * 다운로드 링크
            if(r.bfile != null){
                document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${r.bfile}">${r.bfile}</a>`;
            }
            // * 삭제 / 수정 버튼 활성화 (해당 보고 있는 클라이언트가 작성자의 아이디와 동일하면)
                // 유효성 검사
                // 현재 로그인된 아이디 또는 번호(1.헤더 HTML 가져온다 , 2.서버에 요청한다)
            $.ajax({
                url:'/member/login/check',
                method:'get',
                success:(r2)=>{
                    if(r2 == r.mid){
                        let btnHTML = `<button type="button" class="boardBtn" onclick="location.href='/board/update?bno=${r.bno}'">수정</button>`;
                        btnHTML += `<button type="button" class="boardBtn" onclick="deleteView()">삭제</button>`;
                        document.querySelector('.btnBox').innerHTML = btnHTML;
                    }
                }
            })
        }
    })
}

// 3. 게시물 삭제
function deleteView(){
    $.ajax({
        url:"/board/delete.do",
        method:"delete",
        data:{bno:bno},
        success:(r)=>{
            if(r){
                alert('삭제가 완료 되었습니다.');
                location.href="/board";
            }
        }
    })
}