import java.util.concurrent.*;
class AA
{
    public volatile boolean flag = false;
}
class T18 implements Runnable {
    AA a;
    T18(AA aa) {
        a = aa;
    }
    public void run()
    {
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException e) {
            System.out.println("Task 18 interrupted during sleep ");
        }
        a.flag = true;
    }
}
class T182 implements Runnable {
    AA a;
    T182(AA aa) {
        a = aa;
    }
    public void run() {
        while(true) {
            if(a.flag){
               a.flag = false;
               System.out.println("Changed the contents fo the flag to false");
               break;
            }
        }
    }
}
public class Excercise22 {
    public static void main(String [] args) {
        AA aa = new AA();
        Thread thread1 = new Thread( new T182(aa) );
        Thread thread2 = new Thread( new T18(aa) );
        thread1.start();
        thread2.start();
    }
}


