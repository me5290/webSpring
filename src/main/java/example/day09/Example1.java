package example.day09;

import java.awt.*;

public class Example1 {
    public static void main(String[] args) {
        // 1. 현재 코드를 실행하는 스레드객체 호출
        Thread thread = Thread.currentThread();
        // 2. 현재 코드를 실행하는 스레드객체의 이름
        System.out.println("thread.getName() = " + thread.getName());
        // 3. 작업스레드 생성 4가지 방법
            // 자식 익명 객체 : 부모타입 변수명 = new 부모타입(){재정의};
        Thread threadA = new Thread(){
            @Override
            public void run() { // - 작업스레드가 실행할때 최초로 실행되는 함수 재정의
                Thread thread = Thread.currentThread();
                System.out.println("작업스레드A : "+thread.getName());
            }
        };
        threadA.start();

        // *2. 작업스레드 실행
        System.out.println("3 thread.getName() = " + thread.getName());

        // 주어진 시간동안 스레드 일시정지 .sleep(밀리초) : 정적메소드(static) : 정적메소드 호출하는 방법 : 클래스명
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 0; i < 10; i++){
            toolkit.beep();
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
