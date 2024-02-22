package example.day10.예제2;

public class ThreadA extends Thread{
    private WorkObject workObject;
    public ThreadA(WorkObject workObject){
        setName("ThreadA");
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i<100; i++){
            workObject.methodA();
        }
    }
}
