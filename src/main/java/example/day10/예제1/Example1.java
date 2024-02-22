package example.day10.예제1;

public class Example1 {
    public static void main(String[] args) {
        // 1. 계산기 객체 1개 생성
        Calculator calculator = new Calculator();

        // 2.
        User1Thread user1Thread = new User1Thread();
        user1Thread.setCalculator(calculator);
        user1Thread.start();

        // 3.
        User2Thread user2Thread = new User2Thread();
        user2Thread.setCalculator(calculator);
        user2Thread.start();
    }
}
