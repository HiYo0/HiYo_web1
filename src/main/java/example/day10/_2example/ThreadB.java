package example.day10._2example;

public class ThreadB extends Thread {//class start
    private WorkObject workObject;

    public ThreadB(WorkObject workObject){
        setName("ThreadB");
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            workObject.methodB();
        }
    }
}//class end
