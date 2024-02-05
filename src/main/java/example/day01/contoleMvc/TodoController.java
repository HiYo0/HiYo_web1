package example.day01.contoleMvc;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TodoController {

    private TodoDao todoDao = new TodoDao();

    // 2. 할일등록 함수
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }// method end
    // 3. 할일 출력함수
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }// method end
}
