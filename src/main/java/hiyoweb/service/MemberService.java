package hiyoweb.service;

import hiyoweb.model.dao.MemberDao;
import hiyoweb.model.dto.LoginDto;
import hiyoweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MemberService {

    @Autowired
    private FileService fileService; // 외부 서비스
    @Autowired
    private MemberDao memberDao; // 외부 리포지토리

    // 1. 회원가입 서비스
    public boolean Signup(MemberDto memberDto){
        /*
            1. 첨부파일이 있다 vs 없다
                있다 [ 없로드 성공 했다 vs 실패 했다.]
                    성공 db처리
                    실패 false
         */
        // 1. 파일 처리
            // 만약에 첨부파일 존재 하면
        System.out.println("memberDto.getImg().isEmpty() = " + memberDto.getImg().isEmpty());
        String fileName = "이젠고양이.png";  // 등록한 파일없을때는 기본값 보내기
        if(!memberDto.getImg().isEmpty()) { // 존재하면 파일이름 적용
            fileName = fileService.fileUpload(memberDto.getImg());
            if (fileName != null) {// 업로드 성공 했으면
                // 2. DB 처리
                // dto에 업로드 성공한 파일명을 대입한다.
                memberDto.setUuidFile(fileName);
            }else {return false;}
        }
        memberDto.setUuidFile(fileName);
        return memberDao.signup(memberDto);
    }//method Signup end

    // 2. 로그인 서비스

    // 3. id 로 회원정보 요청 서비스
    public MemberDto doGetLoginInfo(LoginDto loginDto){

        MemberDto result =memberDao.memberNo(loginDto);

        return result;
    }

    // ================ 4. 아이디 중복체크 요청 ================ //
    public boolean doGetFindIdCheck(String id){//True = 중복있음
        return memberDao.doGetFindIdCheck(id);
    }

}//class end


