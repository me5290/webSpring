package book.dao;

import book.dto.ArticleForm;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
