// 페이지 관련 객체
let pageObject = {
    page : 1,           // 현재 페이지
    pageBoardSize : 5,  // 페이지당 표시할 게시물 수
    bcno : 0,           // 현재 카테고리
    key : 'b.btitle',   // 검색 key
    keyword : ''        // 검색 keyword
}

// 1. 전체 출력용 : 함수 - 매개변수 X , 반환 X , 언제 실행할건지 페이지 열릴때
doViewList(1);
function doViewList(page){

    pageObject.page = page;

    $.ajax({
        url:"/board/do",
        method:"get",
        data:pageObject,
        success:(r)=>{
            console.log(r);
            let boardTableBody = document.querySelector('#boardTableBody');

            let html = ``;

            r.list.forEach(board => {
                console.log(board);

//                let nowdate = new Date();
//                let year = nowdate.getFullYear();
//                let month = nowdate.getMonth() + 1;
//                let date = nowdate.getDate();
//                if(month < 10){
//                    String(month).padStart(2,"0");
//                }
//                if(date < 10){
//                    String(date).padStart(2,"0");
//                }
//
//                let dateinfo = year+"-"+month+"-"+date;
//                let savedate = board.bdate.split(" ");
//                console.log(dateinfo);
//                console.log(savedate[0]);
//                console.log(savedate[1]);
//
//                if(dateinfo == savedate[0]){
//                    board.bdate = savedate[1];
//                }
                html += `
                    <tr>
                        <th>${board.bno}</th>
                        <td>
                            <a href="/board/view?bno=${board.bno}">
                                ${board.btitle}
                            </a>
                        </td>
                        <td>
                            <img src="/img/${board.mimg}" style="width:18px; border-radius:50%; margin-right:4px;"/>
                            ${board.mid}
                        </td>
                        <td>${board.bdate}</td>
                        <td>${board.bview}</td>
                    </tr>
                `
            });
            boardTableBody.innerHTML = html;

            // =============== 페이지네이션 구성
            // 1. 어디에
            let pagination = document.querySelector('.pagination');
            // 2. 무엇을
            let pagehtml=``;
                // 이전버튼
            pagehtml += `
                <li class="page-item ${page == 1 ? 'disabled' : ''}">
                    <a class="page-link" onclick="doViewList(${page-1 < 1 ? 1 : page-1})">이전</a>
                </li>
            `;
            for(let i = r.startBtn; i <= r.endBtn; i++){
                // 만약에 i가 현재페이지와 같으면 active 클래스 삽입 아니면 생략
                pagehtml += `
                    <li class="page-item ${i == page ? 'active' : ''}">
                        <a class="page-link" onclick="doViewList(${i})">
                            ${i}
                        </a>
                    </li>
                `;
            }
                // 다음버튼
            pagehtml += `
                <li class="page-item ${page == r.totalPage ? 'disabled' : ''}">
                    <a class="page-link" onclick="doViewList(${page+1 > r.totalPage ? r.totalPage : page+1})">다음</a>
                </li>
            `;

            pagination.innerHTML = pagehtml;

            // 3. 부가 출력
            document.querySelector('.totalPage').innerHTML = r.totalPage;
            document.querySelector('.totalBoardSize').innerHTML = r.totalBoardSize;
        }
    });
    return;
}

// 2. 페이지당 게시물 수
function onPageBoardSize(object){
    console.log(object);
    pageObject.pageBoardSize = object.value;
    doViewList(1);
}

// 3. 카테고리
function onBcno(object){
    // bcno : 카테고리 식별번호 [0] = 없다/전체 , [1~] = 식별번호pk
    pageObject.bcno = object.value;
    // 검색 제거
    pageObject.key = 'b.btitle';
    pageObject.keyword = '';
    document.querySelector('.key').value = 'b.btitle';
    document.querySelector('.keyword').value = '';
    doViewList(1);
}

// 4. 검색
function onSearch(){
    // 1. 입력받은 값 가져오기
    let key = document.querySelector('.key').value;
    let keyword = document.querySelector('.keyword').value;
    // 2. 서버에 전송할 객체에 담아주기
    pageObject.key = key;
    pageObject.keyword = keyword;
    // 3. 출력 함수 호출
    doViewList(1);
}