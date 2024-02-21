package example.day09._3인과제3.model.dao;

import example.day09._3인과제3.model.dto.EmDto;
import example.day09._3인과제3.model.dto.PlusDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // 0. DB 연동
    public EmDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/team3DB",
                    "root",
                    "1234"
            );
            System.out.println("DB연동 성공");
        }catch (Exception e){
            System.out.println("DB연동 실패 : "+e);
        }
    }

    // 1. 사원등록
    public boolean create(EmDto emDto){
        try {
            String sql = "insert into team3Table(name,phone) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,emDto.getName());
            ps.setString(2, emDto.getPhone());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 2. 사원목록 출력
    public List<EmDto> read(){
        List<EmDto> emDtoList = new ArrayList<>();
        try {
            String sql = "select * from team3Table";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                EmDto emDto = new EmDto(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
                );
                emDtoList.add(emDto);
            }
            return emDtoList;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    // 3. 사원 전화번호 수정
    public boolean update(EmDto emDto){
        try {
            String sql = "update team3Table set phone = ? where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, emDto.getPhone());
            ps.setInt(2,emDto.getNo());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 4. 사원삭제
    public boolean delete(int no){
        try {
            String sql = "delete from team3Table where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,no);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 5. 교과 등록
    public boolean plusCreate(PlusDto plusDto){
        try {
            String sql = "insert into team3Plus(no,content,point) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,plusDto.getNo());
            ps.setString(2,plusDto.getContent());
            ps.setString(3, plusDto.getPoint());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 6. 교과 출력
    public List<PlusDto> plusRead(int no){
        List<PlusDto> plusDtoList = new ArrayList<>();
        try {
            String sql = "select * from team3Plus where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,no);
            rs = ps.executeQuery();
            while (rs.next()){
                PlusDto plusDto = new PlusDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                plusDtoList.add(plusDto);
            }
            System.out.println(plusDtoList);
            return plusDtoList;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
