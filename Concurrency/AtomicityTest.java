import java.util.concurrent.*;
public class AtomicityTest implements Runnable
{
private volatile int i=0;
public synchronized int getValue(){return i;}
private synchronized void evenIncrement()
{
    i++;
 //   Thread.yield();
    i++;
}
public void run()
{
    while(true)
        evenIncrement();
}
public static void main(String [] args)
{
    ExecutorService exec = Executors.newCachedThreadPool();
    AtomicityTest aT = new AtomicityTest();
    exec.execute(aT);
    while(true)
    {
        int val = aT.getValue();
        System.out.println(val);
        if(val >= Integer.MAX_VALUE-10)
            System.out.println(val);
        if(val%2 != 0)
        {
            System.out.println(val);
            System.exit(0);
        }
    }
}
}

