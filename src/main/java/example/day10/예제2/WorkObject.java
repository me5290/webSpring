package example.day10.예제2;

public class WorkObject {
    // 1. 함수1
    public synchronized void methodA(){
        // 1. 현재 스레드객체 호출 : .currentThread()
        Thread thread = Thread.currentThread();
        // 2. 스레드의 이름 호출 : .getName()
        System.out.println(thread.getName());
        notify(); // 다른 스레드를 실행 대기 상태로 만듬
        try {
            wait(); // 현재 스레드를 일시 정지 상태로 만듬
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // 2. 함수2
    public synchronized void methodB(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        notify(); // 다른 스레드를 실행 대기 상태로 만듬
        try {
            wait(); // 현재 스레드를 일시 정지 상태로 만듬
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
    스레드란
    멀티스레드란
    동기화 비동기화
    아키텍처
*/