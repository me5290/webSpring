package example.day10.예제1;

public class Calculator {
    private int memory;

    public int getMemory(){
        return memory;
    }

    // synchronized(동기화) : 여러 스레드가 해당 메소드/블록을 호출 했을때 순서 메기기
    public synchronized void setMemory1(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(Thread.currentThread().getName()+" : "+this.memory);
    }

}
