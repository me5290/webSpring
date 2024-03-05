// 1. 전체 출력용 : 함수 - 매개변수 X , 반환 X , 언제 실행할건지 페이지 열릴때
doViewList(1);
function doViewList(page){
    $.ajax({
        url:"/board/do",
        method:"get",
        data:{'page':page},
        success:(r)=>{
            console.log(r);
            let boardTableBody = document.querySelector('#boardTableBody');

            let html = ``;

            r.list.forEach(board => {
                console.log(board);
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
        }
    })
}