/**
 * Created by fengy on 2017/8/13.
 * 尝试的消费者生产者模式demo
 */

class Produce implements Runnable {
    save s=null;
    public Produce(save s){
        this.s=s;
    }
    @Override
    public synchronized void run() {
        if(s.count>=10){
            try {
                synchronized(s){
                    s.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(s.count<10){
            s.add();
        }

        synchronized(s){
            s.notify();
        }

    }
}
class Consume implements Runnable {
    save s=null;
    public Consume(save s){
        this.s=s;
    }
    @Override
    public  void run() {
        if(s.count<=0){
            try {
                synchronized(s){
                    s.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized(s){
            s.notify();
        }
        while(s.count>0)
            s.dec();
    }
}
class save{
    int count=0;
    public save(int count){
        this.count=count;
    }
    public void add(){
        count++;
        System.out.println("生产第"+count+"件产品");
    }
    public void dec(){
        count--;
        System.out.println("消费第"+(count+1)+"件产品");
    }
}
public class Thread{
    public static void main(String[] args){
        save s=new save(0);
        Produce t1=new Produce(s);
        Consume t2=new Consume(s);
        java.lang.Thread thread1=new java.lang.Thread(t1);
        java.lang.Thread thread2=new java.lang.Thread(t2);
        thread1.start();
        thread2.start();
    }
}