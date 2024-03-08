package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BoardDao extends Dao {
    // 1. 글쓰기 처리
    public long doPostBoardWrite(BoardDto boardDto){
        try {
            String sql="insert into board(btitle,bcontent,bfile,mno,bcno) values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setString(3,boardDto.getBfile());
            ps.setLong(4,boardDto.getMno());
            ps.setLong(5,boardDto.getBcno());
            int count = ps.executeUpdate();
            if(count == 1){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    return rs.getLong(1);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // 2. 전체 글 출력 호출
    public List<BoardDto> doGetBoardViewList(int startRow , int pageBoardSize , int bcno , String key , String keyword){
        System.out.println("BoardDao.doGetBoardViewList");
        BoardDto boardDto = null;
        List<BoardDto> list = new ArrayList<>();
        try {
            String sql="select * from board b inner join member m on b.mno = m.no";
                if(bcno > 0){
                    sql +=" where bcno = " + bcno;
                }
                if (!keyword.isEmpty()){
                    System.out.println("검색 키워드 존재");
                    if (bcno > 0){
                        sql += " and ";
                    }else{
                        sql += " where ";
                    }
                    sql += key+" like '%"+keyword+"%'";
                }
                sql +=" order by b.bdate desc limit ? , ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,startRow);
            ps.setInt(2,pageBoardSize);
            rs = ps.executeQuery();
            while (rs.next()){
                boardDto = new BoardDto(
                        rs.getLong("bno"),
                        rs.getString("btitle"),
                        rs.getString("bcontent"),
                        rs.getString("bfile"),
                        rs.getLong("bview"),
                        rs.getString("bdate"),
                        rs.getLong("mno"),
                        rs.getLong("bcno"),
                        null,
                        rs.getString("id"),
                        rs.getString("img")
                );
                list.add(boardDto);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }

    // 2-2. 전체 게시물 수 호출
    public int getBoardSize(int bcno , String key , String keyword){
        System.out.println("bcno = " + bcno + ", key = " + key + ", keyword = " + keyword);
        try {
            String sql="select count(*) from board b inner join member m on b.mno = m.no";
            // 1. 만약에 카테고리 조건이 있으면 where 추가
            if(bcno > 0){
                sql += " where bcno = "+bcno;
            }
            // 2. 만약에 검색이 있을때 key,keyword 추가
            if (!keyword.isEmpty()){
                System.out.println("검색 키워드 존재");
                if (bcno > 0){
                    sql += " and ";
                }else{
                    sql += " where ";
                }
                sql += key+" like '%"+keyword+"%'";
            }

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return 0;
    }

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno){
        BoardDto boardDto = null;
        try {
            String sql="select * from board b inner join member m on b.mno = m.no where b.bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,bno);
            rs = ps.executeQuery();
            if(rs.next()){
                boardDto = new BoardDto(
                        rs.getLong("bno"),
                        rs.getString("btitle"),
                        rs.getString("bcontent"),
                        rs.getString("bfile"),
                        rs.getLong("bview"),
                        rs.getString("bdate"),
                        rs.getLong("mno"),
                        rs.getLong("bcno"),
                        null,
                        rs.getString("id"),
                        rs.getString("img")
                );
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return boardDto;
    }

    public void doPostBview(int bno){
        try {
            String sql="update board set bview = bview+1 where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    // 4. 글 수정 처리
    public boolean doUpdateBoard(BoardDto boardDto){
        System.out.println("BoardDao.doPutBoard");
        try {
            String sql="update board set btitle = ? , bcontent = ? , bcno = ? , bfile = ? where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setLong(3,boardDto.getBcno());
            ps.setString(4,boardDto.getBfile());
            ps.setLong(5,boardDto.getBno());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 5. 글 삭제 처리
    public boolean doDeleteBoard( int bno){
        System.out.println("BoardDao.doDeleteBoard");
        try {
            String sql="delete from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 6. 작성자 인증
    public boolean boardWriterAuth(long bno,String mid){
        try {
            String sql="select * from board b inner join member m on b.mno = m.no where b.bno = ? and m.id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,bno);
            ps.setString(2,mid);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 7. 댓글 작성
    public boolean doPostReplyWrite(Map<String,String > map){
        try {
            String sql = "insert into breply(brcontent , brindex , mno , bno) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,map.get("brcontent"));
            ps.setString(2,map.get("brindex"));
            ps.setString(3,map.get("mno"));
            ps.setString(4,map.get("bno"));
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 8. 댓글 출력
    public List<Map<String ,String >> doGetReplyDo(int bno){
        List<Map<String ,String >> list = new ArrayList<>();
        try {
            // 상위 댓글 먼저 출력
            String sql="select * from breply where bno = ? and brindex = 0";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            rs = ps.executeQuery();
            while (rs.next()){
                Map<String , String > map = new HashMap<>();
                map.put("brno",rs.getString("brno"));
                map.put("brcontent",rs.getString("brcontent"));
                map.put("brdate",rs.getString("brdate"));
                map.put("mno",rs.getString("mno"));

                list.add(map);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }
}
