package example.day08;

import java.awt.*;

public class 작업스레드 implements Runnable {

    // 작업스레드가 최초 실행하는 함수
    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("비프음 ~~");
            try {Thread.sleep(500);}
            catch (InterruptedException e) {throw new RuntimeException(e);}
        }
    }
}
