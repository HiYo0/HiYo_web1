package example.day02.WebMvc;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// 해당 클래스를 스프링 mvc 환경에 등록
@RestController // 스프링 컨테이너[스프링 관리하는 메모리공간] 에 빈(객체) 등록 IOC
                // IOC 제어역전 기법 : 개발자가 객체 관리X , 스프링이 대신
public class TodoController {

    //
    private TodoDao todoDao = new TodoDao();

    // 2. 할일등록 함수
    @PostMapping("/todo/post.do")
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }// method end

    @GetMapping("/todo/get.do")
    // 3. 할일 출력함수
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }// method end

    @PutMapping("/todo/put.do")
    // 4. 할일 상태수정   함수
    public boolean doPut(TodoDto todoDto){
        return todoDao.doPut(todoDto);
    }

    @DeleteMapping("/todo/delete.do")
    // 5. 할일 삭제 함수
    public boolean doDelete(int id){
        return todoDao.doDelete(id);
    }
}
