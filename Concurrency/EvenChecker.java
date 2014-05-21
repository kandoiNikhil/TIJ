import java.util.concurrent.*;

public class EvenChecker implements Runnable
{
    private IntGenerator generator;
    private final int id;
    public EvenChecker(IntGenerator g, int ident)
    {
        generator = g;
        id = ident;
    }
    public void run()
    {
        while(!generator.isCanceled())
        {
            int val = generator.next();
            if(val%2!=0)
            {
                System.out.println(val + "  no even ");
                generator.cancel();
            }
        }
    }
    public static void test(IntGenerator g, int count)
    {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for( int i=0;i<count;i++)
        {
            exec.execute(new EvenChecker(g,i));
        }
            exec.shutdown();
    }
    public static void test(IntGenerator g)
    {
        test(g,10);
    }
}

