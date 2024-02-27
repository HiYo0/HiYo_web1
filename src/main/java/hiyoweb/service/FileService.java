package hiyoweb.service;


import hiyoweb.model.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
@Service // 해당클래스를 스프링 컨테이너(저장소)에 빈 (객체) 등록
public class FileService {//class start
    // Controller : 중계자 역활 ( HTTP매핑 , HTTP요청/응답 , 데이터유효성검사 )등등
    // Service : Controller <-- Service(비지니스로직) --> Dao , Controller <--> Service

    // 어디에( PATH ) 누구를(파일객체)
    String uploadPath = "C:\\Users\\504\\Desktop\\HiYo_web1\\build\\resources\\main\\static\\img\\";

    // 1. 업로드 메소드
    public String fileUpload(MultipartFile multipartFile){

        // 난수 식별코드 생성
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid); // 확인용
        
        // *파일 이름 조합하기 : 새로운 식별이름과 실제 파일 이름

        // 식별키와 실제 이름 구분 : 왜?? 나중에 쪼개서 구분하기위함.[ 다운로드시 식별키 빼고 제공하려고 ]
        // 혹시나 파일 이름이 구문문자가 있을경우 기준이 깨짐
        // .replaceAll() : 문자열 치환/교체
        // 업로드 할 경로 설정( 내장 톰캣 경로 )
        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-");

        // 1. 첨부파일 업로드 하기. [ 업로드란 : 클라이언트의 바이트(대용량/파일)을 복사해서 서버로 ]
        // 1. 첨부파일을 저장할 경로
        // File 클래스 : 파일 관련된 메소드 제공.
        // new File( 파일경로 ) -- 저장시킬 경로 + 저장시킬 이름으로 지정함
        File file = new File(uploadPath + filename);
        System.out.println("file = " + file);

        // 2. [ 무엇을 ] 첨부파일 객체
            // transferTo( 경로 )  --- 지정한 경로에 복사시키기
        try {multipartFile.transferTo( file );
        }catch (Exception e){System.out.println("e = " + e); return null;}

        return filename;
    }
    // 2. 다운로드 메소드

}//class end