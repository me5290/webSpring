console.log('member.js');

/*
    정규표현식 : 특정한 규칙을 가진 문자열의 집합을 표현할때 사용하는 형식 언어
        - 주로 문자열 데이터 검사할때 사용 (유효성 검사)
        - 메소드
            정규표현식.test(검사할대상);
        - 형식 규칙
            /^                      : 정규표현식 시작 알림
            $/                      : 정규표현식 끝 알림
            {최소길이 , 최대길이}    : 문자 길이 규칙
            [허용할문자]             : 허용 문자 규칙
            +                       : 앞에 있는 패턴 1개 이상 반복
            ?                       : 앞에 있는 패턴 0개 혹은 1개 이상 반복
            *                       : 앞에 있는 패턴 0개 반복
            .                       : 1개 문자
            ()                      : 패턴의 그룹
            ?=.*                    : 앞에 있는 패턴 문자 존재 여부 판단

            예시) 비밀번호 : /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{8,30}$/
            예시) 전화번호 : /^([0-9]{2,3})+[-]?([0-9]{3,4})+[-]+([0-9]{4})$/
            예시) 이메일 : /^[a-zA-Z0-9]+@+[a-zA-Z0-9]+\.+[a-z.]+$/
*/

// 현재 유효성검사 체크 현황
let checkArray = [false,false,false,false,false]; // 아이디,비밀번호,이름,전화번호,이메일

// 4. 아이디 유효성검사 (아이디 입력할때마다)
function idCheck(){
    console.log('idCheck()');
    // 1. 입력된 데이터 가져오기
    let id = document.querySelector('#id').value;
    console.log(id);

    // 2. 정규표현식 : 영소문자+숫자5~30
    let idj = /^[a-z0-9]{5,30}$/

    // 3. 정규표현식에 따른 검사
    console.log(idj.test(id));
    if(idj.test(id)){
        // 아이디 중복 체크(ajax)
        $.ajax({
            url:"/member/signup/idcheck",
            method:"get",
            data:{id : id},
            success:(r)=>{ // true : 중복있음 , false : 중복없음
                if(r){
                    document.querySelector('.idcheckbox').innerHTML = `중복 된 아이디입니다.`;
                    document.querySelector('.idcheckbox').style.color='red';
                    checkArray[0]=false;
                }else{
                    document.querySelector('.idcheckbox').innerHTML = `사용 가능한 아이디입니다.`;
                    document.querySelector('.idcheckbox').style.color='green';
                    checkArray[0]=true;
                }
            }    
        })
    }else{
        // 유효성 검사 출력
        document.querySelector('.idcheckbox').innerHTML = `영소문자+숫자 조합의 5~30글자로 입력해주세요.`;
        document.querySelector('.idcheckbox').style.color='red';
        checkArray[0]=false;
    }
}

// 5. 비밀번호 유효성검사
function pwCheck(){
    let pw = document.querySelector('#pw').value;
    let pwconfirm = document.querySelector('#pwconfirm').value;

    // 정규표현식 : 영대소문자1개이상+숫자1개이상 8~30자리
    let pwj = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{8,30}$/

    if(pwj.test(pw)){
        if(pw == pwconfirm){
            document.querySelector('.pwconfirmcheckbox').innerHTML = `비밀번호가 일치합니다.`;
            document.querySelector('.pwconfirmcheckbox').style.color='green';
            checkArray[1]=true;
        }else{
            document.querySelector('.pwconfirmcheckbox').innerHTML = `비밀번호가 일치하지 않습니다.`;
            document.querySelector('.pwconfirmcheckbox').style.color='red';
            checkArray[1]=false;
        }
        document.querySelector('.pwcheckbox').innerHTML = `사용 가능한 비밀번호입니다.`;
        document.querySelector('.pwcheckbox').style.color='green';
    }else{
        document.querySelector('.pwcheckbox').innerHTML = `영대소문자1개이상+숫자1개이상 8~30자리 글자로 입력해주세요.`;
        document.querySelector('.pwcheckbox').style.color='red';
        checkArray[1]=false;
    }
}

// 6. 이름 유효성검사
function nameCheck(){
    let name = document.querySelector('#name').value;
    
    let namej = /^[가-힣]{2,20}$/

    if(namej.test(name)){
        document.querySelector('.namecheckbox').innerHTML = ``;
        checkArray[2]=true;
    }else{
        document.querySelector('.namecheckbox').innerHTML = `2~20 자리의 한글로 입력해주세요.`;
        document.querySelector('.namecheckbox').style.color='red';
        checkArray[2]=false;
    }
}

// 7. 전화번호 유효성검사
function phoneCheck(){
    let phone = document.querySelector('#phone').value;

    let phonej = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]+([0-9]{4})+$/

    if(phonej.test(phone)){
        document.querySelector('.phonecheckbox').innerHTML = ``;
        checkArray[3]=true;
    }else{
        document.querySelector('.phonecheckbox').innerHTML = `\'-\'를 포함한 번호를 입력해주세요.`;
        document.querySelector('.phonecheckbox').style.color='red';
        checkArray[3]=false;
    }
}

let timer = 0;
let authbox = document.querySelector('.authbox');
let send = document.querySelector('.send');

// 8. 이메일 유효성검사
function emailCheck(){
    let email = document.querySelector('#email').value;

    let emailj = /^[a-zA-Z0-9]+@+[a-zA-Z0-9]+\.+[a-z.]+$/

    if(emailj.test(email)){
        document.querySelector('.emailcheckbox').innerHTML = `올바른 형식입니다.`;
        document.querySelector('.emailcheckbox').style.color='green';
        send.disabled = false;
        send.style.cursor="pointer";

    }else{
        document.querySelector('.emailcheckbox').innerHTML = `\'@\'와\'.\'을 포함한 이메일 형식으로 입력해주세요.`;
        document.querySelector('.emailcheckbox').style.color='red';
        send.disabled = true;
        checkArray[4]=false;
    }
}

// 9. 인증요청
function authreq(){
    console.log('인증');
    // 1. 

    // 2. 
    let html = `
        <p>인증번호</p>
        <input onkeyup="" type="text" id="certi" name="certi"/>
        <button class="send" type="button" onclick="auth()">
            인증
        </button>
        <span class="timebox">02분00초</span>
    `;

    // 3. 
    authbox.innerHTML = html;

    // ======== 자바에게 인증 요청 ========= //
    $.ajax({
        url:"/auth/email/request",
        method:"get",
        data : {email : document.querySelector('#email').value},
        success:(r)=>{
            if(r){
                // 4. 타이머 함수 실행
                timer = 120;
                ontimer();

                // 해당 버튼 사용 금지
                send.disabled = true;
            }else{
                alert('관리자에게 문의');
            }
        }
    })
    // ==================================== //
}
let interval;
// 10. 타이머
function ontimer(){
    interval = setInterval(() => {
        // 1. 초 변수를 분/초로 변환
        let m = parseInt(timer/60); // 분
        let s = parseInt(timer%60); // 분 제외한 초
        
        // 2. 시간을 두 자릿수로 표현
        m = m < 10 ? "0"+m : m;
        s = s < 10 ? "0"+s : s;
    
        // 3. 시간 출력
        document.querySelector('.timebox').innerHTML = `${m}분${s}초`;
        
        // 4. 초 감소
        timer--;
    
        // 5. 만약에 초가 0보다 작아지면 종료
        if(timer < 0){
            clearInterval(interval);
            authbox.innerHTML = `다시 인증을 요청 해주세요.`;
            send.disabled = false;
        }
    } , 1000);
}

// 11. 인증 함수
function auth(){
    // 1. 내가 입력한 인증번호
    let certi = document.querySelector('#certi').value;
    // == 내가 입력한 인증번호를 자바에게 보내기 == //
    $.ajax({
        url:"/auth/email/check",
        method:"get",
        data : {certi : certi},
        success:(r)=>{
            // 3. 성공시 / 실패시
            if(r){
                checkArray[4]=true;
                document.querySelector('.emailcheckbox').innerHTML = `인증완료`;
                document.querySelector('.emailcheckbox').style.color='gray';
                clearInterval(interval);
                authbox.innerHTML = '';
                send.disabled = true;
            }else{
                checkArray[4]=false;
                alert('인증번호가 틀립니다.');
            }
        }
    })
    // ========================================== //
}

// 테스트
    // setInterval(함수,밀리초) : 특정 밀리초 마다 함수 실행
    // clearInterval(Interval변수) : 종료
// let timer = 3;

// let interval = setInterval(() => {
//     // 1. 초 변수를 분/초로 변환
//     let m = parseInt(timer/60); // 분
//     let s = parseInt(timer%60); // 분 제외한 초
    
//     // 2. 시간을 두 자릿수로 표현
//     m = m < 10 ? "0"+m : m;
//     s = s < 10 ? "0"+s : s;

//     // 3. 시간 출력
//     document.querySelector('.authbox').innerHTML = `${m}분${s}초`;
    
//     // 4. 초 감소
//     timer--;

//     // 5. 만약에 초가 0보다 작아지면 종료
//     if(timer < 0){
//         clearInterval(interval);
//     }
// } , 1000);

// 1. 회원가입
function signup(){
    // 유효성검사 체크 현황중에 하나라도 false이면 회원가입 금지
    for(let i = 0; i < checkArray.length; i++){
        if(!checkArray[i]){
            alert('잘못 입력되었습니다 다시 확인해주세요.');
            return;
        }
    }

    console.log("signup()");
    // 1. Html 입력값 호출[document.querySelector()]
        // 1. 데이터 하나씩 가져오기
//        let id = document.querySelector('#id').value;
//        console.log(id);
//        let pw = document.querySelector('#pw').value;
//        console.log(pw);
//        let name = document.querySelector('#name').value;
//        console.log(name);
//        let phone = document.querySelector('#phone').value;
//        console.log(phone);
//        let email = document.querySelector('#email').value;
//        console.log(email);
//        let img = document.querySelector('#img').value;
//        console.log(img);
        // 2. 폼 가져오기
        let signUpForm = document.querySelectorAll('.signUpForm');
        console.log(signUpForm);
        let signUpFormData = new FormData(signUpForm[0]);
        console.log(signUpFormData);

    // 2. 객체화 [let info = { }]
    // let info = {
    //     id : id,
    //     pw : pw,
    //     name : name,
    //     phone : phone,
    //     email : email,
    //     img : img
    // };
    // console.log(info);

    // 3. controller 통신 (AJAX)
    $.ajax({
        url : '/member/signup',
        method : 'POST',
        //data : info,
        data : signUpFormData,
        contentType : false,
        processData : false,
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

// 2. 로그인
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
                location.href="/";      // 로그인 성공시 메인페이지로 이동
            }else{
                alert('로그인 실패');
            }
        }
    })
}

function onChangeImg(event){
    console.log("preimg()");
    console.log(event);             // 현재 함수를 실행한 input
    console.log(event.files);       // 현재 input의 첨부파일들
    console.log(event.files[0]);    // 첨부파일들 중에서 첫번째 파일
    // - input에 업로드 된 파일을 바이트로 가져오기
        // new FileReader() : 파일 읽기 관련 메소드 제공
    // 1. 파일 읽기 객체 생성
    let fileReader = new FileReader();
    // 2. 파일 읽기 메소드
    fileReader.readAsDataURL(event.files[0]);
    console.log(fileReader);
    console.log(fileReader.result);
    // 3. 파일 onload 정의
    fileReader.onload = e => {
        console.log(e);                 // ProgressEvent
        console.log(e.target);
        console.log(e.target.result);   // 여기에 읽어온 첨부파일 바이트
        document.querySelector('#preimg').src = e.target.result;
    }
}