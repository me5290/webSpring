package ezenweb.service;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class MemberService {
    @Autowired
    private FileService fileService;
    @Autowired
    private MemberDao memberDao;

    // 1. 회원가입 서비스
    public boolean doPostSignup(MemberDto memberDto){
        // 1. 파일처리
            // 만약에 첨부파일이 존재하면
        String fileName = "default.jpg";
        if(!memberDto.getImg().isEmpty()){
            fileName = fileService.fileUpload(memberDto.getImg());
            if (fileName == null){ // 업로드 실패했으면
                return false;
            }
        }
        // 2. DB처리
        // dto에 업로드 성공한 파일명을 대입
        memberDto.setUuidFile(fileName);
        return memberDao.doPostSignup(memberDto);
    }

    // 2. 로그인 서비스

    // 3. 회원정보 요청 서비스
    public MemberDto doGetLoginInfo(String id){
        // 1. DAO 호출
        return memberDao.doGetLoginInfo(id);
    }

    // 4. 아이디 중복 체크 요청
    public boolean doGetIdCheck(String id){
        return memberDao.doGetIdCheck(id);
    }
}
