package example.day09;

import lombok.*;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter@ToString
public class SumThread extends Thread {
    private long sum;

    // 1~100까지 누적합을 구하는 함수
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
    }

}
