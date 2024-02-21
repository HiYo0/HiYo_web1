package example.day09;

public class WorkThread extends Thread{//class start

    // 1.
    public boolean work = true;

    public WorkThread(String name){
        setName(name);

        // Thread 클래스
        // setName();   // 스레드 이름 변경함수
        // getName();   // 스레드 이름 호출 함수.
        // run();       // 작업스레드가 실행할 코드 함수.
    }

    @Override
    public void run() {
        while (true){
            try {Thread.sleep(1000);} catch (InterruptedException e) {System.out.println("e = " + e);}
            if(work){
                System.out.println(getName());
            }else {
                System.out.println("1");
                Thread.yield();
                System.out.println("2");}

        }//while end
    }//run method end

}//class end
