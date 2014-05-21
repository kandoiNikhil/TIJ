import java.util.concurrent.locks.*;
class Task16
{
    private Object syncObject = new Object();
    private Lock lock1 = new ReentrantLock();;
    private Lock lock2 = new ReentrantLock();;
    public void f()
    {
       lock1.lock();
          try 
        {
            for(int i=0;i<10;i++)
            {
            System.out.println("f()");
            Thread.yield();
            }

        }
       finally
       {
           lock1.unlock();
       }

    }
    public void g()
    {
        lock1.lock();
            try
        {
            for(int i=0;i<10;i++)
            {
            System.out.println("g()");
            Thread.yield();
            }

       }
       finally
       {
           lock1.unlock();
       }

    }
    public void h()
    {
        lock2.lock();
            try
        {
            for(int i=0;i<10;i++)
            {
            System.out.println("h()");
            Thread.yield();
            }

        }
        finally
        {
            lock2.unlock();
        }

    }

}
public class Excercise16
{
    public static void main(String [] args)
    {
        final Task16 t = new Task16();
        new Thread() {
            public void run()
            {
                t.f();
            }
        }.start();
     new Thread() {
            public void run()
            {
                t.g();
            }
        }.start();
     new Thread() {
            public void run()
            {
                t.h();
            }
        }.start();

    }
}
