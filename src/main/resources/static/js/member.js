console.log('member.js');

// 1. 회원가입
function signup(){
    console.log("signup()");
    // 1. Html 입력값 호출[document.querySelector()]
    let id = document.querySelector('#id').value;
    console.log(id);
    let pw = document.querySelector('#pw').value;
    console.log(pw);
    let name = document.querySelector('#name').value;
    console.log(name);
    let phone = document.querySelector('#phone').value;
    console.log(phone);
    let email = document.querySelector('#email').value;
    console.log(email);
    let img = document.querySelector('#img').value;
    console.log(img);

    // 2. 객체화 [let info = { }]
    let info = {
        id : id,
        pw : pw,
        name : name,
        phone : phone,
        email : email,
        img : img
    };
    console.log(info);

    // 3. controller 통신 (AJAX)
    $.ajax({
        url : '/member/signup',
        method : 'POST',
        data : info,
        success : function ( result ){
            console.log(result);
            // 4. 결과
            if(result){
                alert('회원가입 성공');
                location.href = '/member/login';
            }else{
                alert('회원가입 실패');
            }
        }
    })
    /*
        $.ajax({
           url : '서버 매핑 주소 ',
           method : '서버 매핑 방법',
           data :  서버 요청 보낼 매개변수  ,
           success : function ( 서버 응답 받은 매개변수 ){ }
        })
    */
}

function login(){
    console.log('login()');

    // 1. Html 입력값 호출
    let id = document.querySelector('#id').value;
    console.log(id);
    let pw = document.querySelector('#pw').value;
    console.log(pw);

    // 2. 객체화
    let info = {
        id : id,
        pw : pw
    };
    console.log(info);

    // 3. controller 서버와 통신
    $.ajax({
        url : '/member/login',          // 어디에
        method : 'POST',                // 어떻게
        data : info,                    // 무엇을 보낼지
        success : function ( result ){  // 무엇을 받을지
            console.log(result);
            // 4. 결과
            if(result){
                alert('로그인 성공');
            }else{
                alert('로그인 실패');
            }
        }
    })
}