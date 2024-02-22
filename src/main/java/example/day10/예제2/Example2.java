package example.day10.예제2;

public class Example2 {
    public static void main(String[] args) {
        // 1. 공유객체 1개 생성
        WorkObject workObject = new WorkObject();

        // 2. 각 스레드 2개 생성
        ThreadA threadA = new ThreadA(workObject);
        ThreadB threadB = new ThreadB(workObject);

        // 3. 스레드 실행
        threadA.start();
        threadB.start();
    }
}
