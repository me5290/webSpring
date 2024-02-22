package example.day10.예제2;

public class ThreadB extends Thread{
    private WorkObject workObject;
    public ThreadB(WorkObject workObject){
        setName("ThreadB");
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i<100; i++){
            workObject.methodB();
        }
    }
}
