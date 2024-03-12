// 1. 로그인 여부 확인 요청 [JS열릴때마다 실행]
$.ajax({
    url:"/member/login/check",
    method:"get",
    async:false,
    success:(r)=>{
        console.log(r);

        let loginMenu = document.querySelector("#loginMenu");

        let html=``;

        if(r != ''){ // 로그인했을때
            $.ajax({
                url : '/member/login/info',
                method : 'get',
                data : {id:r},
                async : false, // 응답이 오기전까지 대기 상태
                success : (r2) => {
                    console.log(r2);
                    console.log(r2.uuidFile);

                    html += `
                        <li id="userName" class="nav-item">
                            <img src="/img/${r2.uuidFile}" width="26px"/> ${r} 님
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">내정보</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" onclick="logout()">로그아웃</a>
                        </li>
                    `;
                }
            })
        }else{ // 로그인안했을때
            html += `
                <li class="nav-item">
                    <a class="nav-link" href="/member/login">로그인</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/member/signup">회원가입</a>
                </li>
            `;
        }

        loginMenu.innerHTML = html;
    }
})

// 2. 로그아웃
function logout(){
    $.ajax({
        url : '/member/logout',
        method : 'get',
        success : (r) => {
            if(r){
                alert("로그아웃 했습니다.");
                location.href='/';
            }else{
                alert("로그아웃 실패[관리자에게 문의]");
            }
        }
    })
}