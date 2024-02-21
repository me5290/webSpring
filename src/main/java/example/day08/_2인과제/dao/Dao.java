package example.day08._2인과제.dao;

import book.dto.ArticleForm;
import example.day08._2인과제.dto.Dto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class Dao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Dao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/todo",
                    "root",
                    "1234"
            );
            System.out.println("DB연동 성공");
        }catch (Exception e){
            System.out.println("DB연동 실패 : "+e);
        }
    }

    public List<Dto> viewList(){
        List<Dto> dtos = new ArrayList<>();
        try {
            String sql = "select * from todolist";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()){
                Dto dto = new Dto(
                        rs.getInt("no"),
                        rs.getString("content"),
                        rs.getString("date"),
                        rs.getBoolean("state")
                );
                dtos.add(dto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return dtos;
    }

    public boolean createList(Dto dto){
        try {
            String sql = "insert into todolist(content,date) values(?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1,dto.getContent());
            ps.setString(2,dto.getDate());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean stateList(int no){
        try {
            String sql = "select state from todolist where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,no);
            rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getBoolean("state")==false){
                    String stateT = "update todolist set state = ? where no = ?";
                    ps = conn.prepareStatement(stateT);
                    ps.setBoolean(1,true);
                    ps.setInt(2,no);
                    ps.executeUpdate();
                }else if(rs.getBoolean("state")==true){
                    String stateF = "update todolist set state = ? where no = ?";
                    ps = conn.prepareStatement(stateF);
                    ps.setBoolean(1,false);
                    ps.setInt(2,no);
                    ps.executeUpdate();
                }
            }
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("다오 상태 실패");
        return false;
    }

    public boolean updateList(Dto dto){
        try {
            String sql = "update todolist set content = ? , date = ? where no = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,dto.getContent());
            ps.setString(2,dto.getDate());
            ps.setInt(3,dto.getNo());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("다오 수정 실패");
        System.out.println(dto);
        return false;
    }

    public boolean deleteList(int no){
        try {
            String sql = "delete from todolist where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,no);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("다오 삭제 실패");
        return false;
    }
}
