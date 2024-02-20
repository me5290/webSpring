function todoCreate(){
    let content = document.querySelector('#content').value;
    console.log(content);
    let date = document.querySelector('#date').value;
    console.log(date);

    // 2. 객체화 [let info = { }]
    let info = {
        content : content,
        date : date,
    };

    $.ajax({
       url : '/todolist/create',   // controller 매핑 주소
       method : 'POST',         // controller POST , GET 매핑
       data :  info ,           // controller 매개변수
       success : function ( result ){ console.log(result);  // controller return
           // 4. 결과
           if(result){
               alert('등록 성공!')
           }else{
               alert('등록 실패')
           } // if 끝
       } // 익명함수 끝
    }) // ajax 끝
}

todoShow();
function todoShow(){
    $.ajax({
           url : '/todolist/get',   // controller 매핑 주소
           method : 'get',         // controller POST , GET 매핑
           success : function ( result ){ console.log(result);  // controller return
                let tbody = document.querySelector('.tbody');

                let html = '';
                for( let i = 0 ; i<result.length; i++ ){
                    if(result[i].state == false){
                        result[i].state = "미완료";
                    }else if(result[i].state == true){
                        result[i].state = "완료";
                    }
                    html += `
                    <tr>
                         <td>${result[i].no}</td>
                         <td>${result[i].content}</td>
                         <td>${result[i].state}</td>
                         <td>${result[i].date}</td>
                         <td><button type="button" onclick="">수정</button><button type="button" onclick="todoDelete(${result[i].no})">삭제</button></td>
                     </tr>`;
                }
                tbody.innerHTML = html;
           } // 익명함수 끝
        }) // ajax 끝
}

//function todoUpdate(){
//    $.ajax({
//           url : '/todolist/update',   // controller 매핑 주소
//           method : 'POST',         // controller POST , GET 매핑
//           data :  info ,           // controller 매개변수
//           success : function ( result ){ console.log(result);  // controller return
//               // 4. 결과
//               if(result){
//                   alert('등록 성공!')
//               }else{
//                   alert('등록 실패')
//               } // if 끝
//           } // 익명함수 끝
//        })
//}

function todoDelete(){
    $.ajax({
           url : '/todolist/delete',   // controller 매핑 주소
           method : 'POST',         // controller POST , GET 매핑
           success : function ( result ){ console.log(result);  // controller return
               // 4. 결과
               if(result){
                   alert('삭제 성공!')
               }else{
                   alert('삭제 실패')
               } // if 끝
           } // 익명함수 끝
        })
}