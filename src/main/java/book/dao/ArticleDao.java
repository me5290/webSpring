package book.dao;

import book.dto.ArticleForm;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component // 스프링 컨테이너에 해당 클래스를 빈(객체)등록
public class ArticleDao {
    // 1. 필드
    private Connection conn;    // DB 연동 인터페이스
    private PreparedStatement ps;   // SQL 실행,매개변수 인터페이스
    private ResultSet rs;       // SQL 실행결과를 호출하는 인터페이스
    public ArticleDao(){
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

    public ArticleForm createArticle(ArticleForm form){
        System.out.println("form = " + form);
        System.out.println("ArticleDao.createArticle");
        // 성공시 반환할 DTO
        ArticleForm saved = new ArticleForm();
        try {
            // 1.
            String sql = "insert into article(title,content) values(?,?);";
            // 2.
            // ps = conn.prepareStatement(sql);
            // insert 된 auto_increment 자동번호 식별키 호출하는 방법
                // 1. SQL 기재 할때 자동으로 생성된 키 호출 선언
                // 2. rs = ps.getGeneratedKeys(); 이용한 생성된 키 목록 반환
                // 3. rs.next() --> rs.get타입(1) : 방금 생성된 키 반환
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);    // 1
            // 3.
            ps.setString(1,form.getTitle());
            ps.setString(2,form.getContent());
            // 4.
            int count = ps.executeUpdate();

            rs = ps.getGeneratedKeys();     // 2
            if (rs.next()){     // 3
                System.out.println("방금 생성된 pk : " + rs.getLong(1));
                Long id = rs.getLong(1);
                saved.setId(id);
                saved.setTitle(form.getTitle());
                saved.setContent(form.getContent());
                return saved;
            }
            // 5.
            // if(count == 1)return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    // 2. 개별 글 조회 : 매개변수(조회할게시물번호(id)) , 반환(조회한게시물정보 1개(DTO))
    public ArticleForm show(Long id){
        try {
            String sql = "select * from article where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if(rs.next()){  // 1개의 게시물을 조회할 예정이라서 next() 한번 처리
                ArticleForm form = new ArticleForm(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                return form;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    // 3. 전체 글 조회 : 매개변수X , 리턴타입(ArrayList)
    public List<ArticleForm> index(){
        List<ArticleForm> list = new ArrayList<>();
        try {
            String sql = "select * from article";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                ArticleForm form = new ArticleForm(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                list.add(form);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }
}
