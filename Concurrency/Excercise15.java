class Task15
{
    private Object syncObject = new Object();
    public void f()
    {
        synchronized(syncObject)
        {
            for(int i=0;i<10;i++)
            {
            System.out.println("f()");
            Thread.yield();
            }

        }

    }
    public void g()
    {
        synchronized(this)
        {
            for(int i=0;i<10;i++)
            {
            System.out.println("g()");
            Thread.yield();
            }

       }

    }
    public void h()
    {
        synchronized(syncObject)
        {
            for(int i=0;i<10;i++)
            {
            System.out.println("h()");
            Thread.yield();
            }

        }

    }

}
public class Excercise15
{
    public static void main(String [] args)
    {
        final Task15 t = new Task15();
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
