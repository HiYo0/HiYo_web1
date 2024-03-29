package example.day09;

public class Example2 {//class start

    public static void main(String[] args) {//main start
        // 1. 스레드 개개체 생성
        SumThread sumThread = new SumThread();

        // 2. 스레드 실행
        sumThread.start();

            // main 스레드에게 작업스레드가 끝날때 까지 기다림.
        try {
            sumThread.join();   // main 스레드와 sumThread스레드가 JOIN
        }catch (Exception e){System.out.println(e);}
        
        // 3. 작업스레드 run() 메소드를 처리하기 전에 아래 실행문 처리.

        System.out.println("sumThread.getSum() = " + sumThread.getSum());

        // p.606 다른 스레드에게 실행 양보

        // 1. 작업스레스 2개 객체 생성
        WorkThread workThreadA = new WorkThread("workThreadA");
        WorkThread workThreadB = new WorkThread("workThreadB");

        // 2. 각 스레드 실행
        workThreadA.start();
        workThreadB.start();

        // 3. 5초 뒤에 A작업스레드의 작업을 양보하기
        try {Thread.sleep(5000);} catch (InterruptedException e) {System.out.println("e = " + e);}
        workThreadA.work = false;

        // 4. 10 초뒤에 A작업 스레드의 작업을 양보하기
        try {Thread.sleep(10000);} catch (InterruptedException e) {System.out.println("e = " + e);}
        workThreadA.work = true;

    }//main end
}//class emd
