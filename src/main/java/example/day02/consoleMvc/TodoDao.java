package example.day02.consoleMvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// 데이터 접근 객체 : DB에 접근 sql(비즈니스 로직) 역할
public class TodoDao {
    // 1. 필드
    private Connection conn;    // DB 연동 인터페이스
    private PreparedStatement ps;   // SQL 실행,매개변수 인터페이스
    private ResultSet rs;       // SQL 실행결과를 호출하는 인터페이스

    // 2. 생성자
    public TodoDao(){
        try {
            // 1. jdbc 라이브러리 호출
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root",
                    "1234"
            );
            System.out.println("DB연동 성공");
        }catch (Exception e){
            System.out.println("DB연동 실패 : "+e);
        }
    }

    // 3. 메소드

    // 2. 할일등록 함수
    public boolean doPost(TodoDto todoDto){
        try {
            // 1. SQL 작성
            String sql = "insert into todo(content,deadline) values(?,?)";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            ps.setString(1,todoDto.getContent());
            ps.setString(2,todoDto.getDeadline());
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 실행 결과
            if(count == 1){
                return true;
            }
            // 6. 함수 리턴
        }catch (Exception e){
            System.out.println(e);
        }

        return false;
    }

    // 3. 할일목록출력 함수
    public ArrayList<TodoDto> doGet(){
        // 0. 반환할 todoList 객체
        ArrayList<TodoDto> list = new ArrayList<>();

        try {
            // 1. SQL 작성
            String sql = "select * from todo";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            // 4. SQL 실행
            rs = ps.executeQuery();
            // 5. SQL 실행 결과
            while (rs.next()){ // next() 레코드 이동
                // 레코드 1개 == dto 1개
                TodoDto todoDto = new TodoDto(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getString("deadline"),
                        rs.getBoolean("state")
                );
                // while문 끝나면 dto는 사라짐 , while문 밖에있는 객체로 이동
                list.add(todoDto);
            }
            // 6. 함수 리턴
        }catch (Exception e){
            System.out.println(e);
        }
        // 레코드 1개 == TodoDto 1개
        // 레코드여러개 == List<TodoDto>
        return list;    // 리스트 반환
    }

    // 4. 할일 상태 수정
    public boolean doPut(TodoDto todoDto){
        try {
            // 1. SQL 작성
            String sql = "update todo set state = ? where id = ?;";

            // 2. SQL 기재
            ps=conn.prepareStatement(sql);

            // 3. SQL 매개변수 정의
            ps.setBoolean(1,todoDto.isState());
            ps.setInt(2,todoDto.getId());

            // 4. SQL 실행
            int count = ps.executeUpdate();

            // 5. SQl 실행 결과
            if(count == 1){
                return true;
            }

            // 6. 함수리턴
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 5. 할일 삭제
    public boolean doDelete(int id){
        try {
            // 1. SQL 작성
            String sql = "delete from todo where id = ?";

            // 2. SQL 기재
            ps=conn.prepareStatement(sql);

            // 3. SQL 매개변수 정의
            ps.setInt(1,id);

            // 4. SQL 실행
            int count = ps.executeUpdate();

            // 5. SQl 실행 결과
            if(count == 1){
                return true;
            }

            // 6. 함수리턴
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
