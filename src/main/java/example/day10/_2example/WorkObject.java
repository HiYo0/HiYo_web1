package example.day10._2example;

public class WorkObject {//class start
    public synchronized void methodA(){
        // 1. 현재 스레드객체 호출 : currentThread()
        // 2. 스레드의 이름 호출 : .getName()
        Thread thread =Thread.currentThread();
        System.out.println(thread.getName() + ": methodA 작업실행");
        notify(); // 다른 스레드를 실행 대기상태로
        try {wait();}// 현재 스레드를 일시 정지 상태로
        catch (InterruptedException e) {throw new RuntimeException(e);}

    }

    public synchronized void methodB(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ": methodB 작업실행");
        notify(); // 다른 스레드를 실행 대기상태로
        try {wait();}// 현재 스레드를 일시 정지 상태로
        catch (InterruptedException e) {throw new RuntimeException(e);}
    }
}//class end
/*

    스레드란
    머티스레드란
    동기화 비동기화
    스렐드 상태 :  실행대기 , 실행 , 일시정지

 */