package example.day08;

public class 작업스레드2 extends Thread {//class start
    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("비프음 ~~");
            try {Thread.sleep(500);}
            catch (InterruptedException e) {throw new RuntimeException(e);}
        }
    }
}// class end
