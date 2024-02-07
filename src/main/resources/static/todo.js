console.log('todo js실행');

// 1. 할일등록 함수
function doPost(){
    console.log('doPost()');
    // 1. 입력받은 값
    let content = document.querySelector('#content').value;
    let deadline = document.querySelector('#deadline').value;

    // 2. 객체화
    let info={
        content : content,
        deadline : deadline
    };
    console.log(info)

    // 3. 컨트롤에게 요청/응답
        // HTTP통신 : 어디에(action/url) 어떻게(method/method) 보낼데이터(name/data) 응답데이터(x/success)
    $.ajax({
        url : '/todo/post.do',
        method : 'post',
        data : info,
        success : function(result){
            if(result == true){
                doGet();
            }
        }
    })
    // 4. 출력
}

// 2. 할일목록출력 함수
doGet();
function doGet(){
    // - 스프링(자바)와 통신(주고 받고)
    // JQUERY AJAX
    // $.ajax(JSON형식의 통신정보)
    /*
        $.ajax({
                method: 'HTTP method'/통신 방법,
                url: spring controller url/통신 대상 식별,
                data: 'HTTP request value/통신 요청으로 보낼 데이터',
                success: HTTP response function/통신 응답 함수
            })
    */
//    $.ajax({
//        method:'spring controller mapping 주소'
//        url:'mapping 방법'
//        data:'보낼 데이터'
//        success:function('받을 데이터'){}
//    })
    $.ajax({
        method:'get',
        url:'/todo/get.do',
        success:function result(resultValue){
            console.log(resultValue);
            // 1. 어디에
            let tbody = document.querySelector('table tbody');
            // 2. 무엇을
            let html = ``;
            for(let i = 0; i < resultValue.length; i++){
                html += `
                    <tr>
                        <td>${resultValue[i].id}</td>
                        <td>${resultValue[i].content}</td>
                        <td>${resultValue[i].deadline}</td>
                        <td>${resultValue[i].state}</td>
                    </tr>
                `
            };
            // 3. 대입
            tbody.innerHTML = html;
        }
    })
}

// 3. 할일상태수정 함수
function doPut(){

}

// 4. 할일삭제 함수
function doDelete(){

}