console.log("JS실행");

    // 1. 함수 : function 함수명() {}
    // 2. 익명 : function () {}
    // 3. 화살표 : () => {}

let id = 9;
let content = "AJAX 테스트중";
// 1. 간단한 통신
function ajax1(){
    console.log("ajax1");
    $.ajax({
        url:"/day11/ajax1",
        method:"get",
        success:(result) => {
            console.log(result);
        },
        error : (error) => {
            console.log(error);
        }
    })
}

// 2. 경로상에 매개변수 포함하기
function ajax2(){
    $.ajax({
        url:`/day11/ajax2/${id}/${content}`,
        method:"get",
        success:(r)=>{
            console.log(r);
        }
    })
}

// 3. 경로상에 쿼리스트링 포함하기
function ajax3(){
    $.ajax({
        url:`/day11/ajax3?id=${id}&content=${content}`,
        method:"get",
        success:(r)=>{
            console.log(r);
        }
    })
}

// 4. HTTP 본문(body)에 객체 보내기
function ajax4(){
    $.ajax({
        url:"day11/ajax4",
        method:"get",
        data:{id:id,content:content},
        success:(r)=>{
            console.log(r);
        }
    })
}

// 5. body(본문)에 데이터 보내는 방식 contentType = form
function ajax5(){
    $.ajax({
        url:"/day11/ajax5",
        method:"post",
        data:{id:id,content:content},
        success:(r)=>{
            console.log(r);
        }
    })
}

// 6.
function ajax6(){
    $.ajax({
        url:"/day11/ajax6",
        method:"post",
        data:JSON.stringify({id:id,content:content}),
        contentType : "application/json",
        success:(r)=>{
            console.log(r);
            JSON.parse(r);
        }
    })
}