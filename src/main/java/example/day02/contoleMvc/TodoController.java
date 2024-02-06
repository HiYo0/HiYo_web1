package example.day02.contoleMvc;

import java.util.ArrayList;

public class TodoController {

    //
    private TodoDao todoDao = new TodoDao();

    // 2. 할일등록 함수
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }// method end
    // 3. 할일 출력함수
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }// method end

    // 4. 할일 상태수정   함수
    public boolean doPut(TodoDto todoDto){
        return todoDao.doPut(todoDto);
    }
    // 5. 할일 삭제 함수
    public boolean doDelete(int id){
        return todoDao.doDelete(id);
    }
}
