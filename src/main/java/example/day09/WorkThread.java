package example.day09;

public class WorkThread extends Thread{
    public boolean work = true;

    public WorkThread(String name) {
        setName(name); // 매개변수로 스레드 이름 변경
        // Thread 클래스
        // setName() : 스레드 이름 변경 함수
        // getName() : 스레드 이름 호출 함수
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println(e);
            }
            if (work){
                System.out.println(getName() + " : 작업처리");
            }else {
                System.out.println("1");
                Thread.yield();
                System.out.println("2");
            }
        }
    }
}
