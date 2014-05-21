public class Task1 implements Runnable
{
    public Task1()
    {
        System.out.println("Thread starts");
    }
    public void run()
    {
        for(int i=0;i<3;i++)
        {
            System.out.println("Printing a message");
            Thread.yield();
        }
        System.out.println("Thread ends");
    }
}

