package example.day10._1example;

public class Example1 {//class start

    public static void main(String[] args) {//main start
        Calculator calculator = new Calculator();
        
        // 1.
        User1Thread user1Thread = new User1Thread();
        
        // 2.
        User2Thread user2Thread = new User2Thread();

        user1Thread.setCalculator(calculator);
        user1Thread.start();// 계산기 100 저장
        
        user2Thread.setCalculator(calculator);
        user2Thread.start();// 계산기 50저장
        
    }//main end
}//class end
