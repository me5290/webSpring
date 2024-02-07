package book.dao;

import book.dto.ArticleForm;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public boolean createArticle(ArticleForm form){
        System.out.println("form = " + form);
        System.out.println("ArticleDao.createArticle");
        try {
            // 1.
            String sql = "insert into article(title,content) values(?,?);";
            // 2.
            ps = conn.prepareStatement(sql);
            // 3.
            ps.setString(1,form.getTitle());
            ps.setString(2,form.getContent());
            // 4.
            ps.executeUpdate();
            // 5.
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
