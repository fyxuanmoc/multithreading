/**
 * Created by fengy on 2017/8/20.
 * start和run的区别：
 */
import java.lang.Thread;
class MyThread extends Thread{
    public MyThread(String name) {
        super(name);
    }

    public void run(){
        System.out.println(Thread.currentThread().getName()+" is running");
    }
};

public class Demo1 {
    public static void main(String[] args) {
        Thread mythread=new MyThread("mythread");

        System.out.println(Thread.currentThread().getName()+" call mythread.run()");
        mythread.run();

        System.out.println(Thread.currentThread().getName()+" call mythread.start()");
        mythread.start();
    }
}
