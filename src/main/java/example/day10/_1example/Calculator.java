package example.day10._1example;

import lombok.Getter;

@Getter
public class Calculator {//class start
    private int memory;

    // synchronized : 동기화 : 여러 스레드가 해당 메소드/블록 호출했을때 순서매기기
        // 컬렉션 프레임워크 : List(Vector) , map( hashTable )
    public synchronized void setMemory(int memory){

        this.memory = memory;
        try {Thread.sleep(2000);} catch (InterruptedException e) {throw new RuntimeException(e);}

        System.out.println(Thread.currentThread().getName()+" : "+this.memory);
    }


}//class end