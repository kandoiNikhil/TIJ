public class Task2 implements Callable<Integer>
{
    private int n;
    public Task2(int n)
    {
        this.n = n ; 
    }
    public int fib(int i)
    {
        if(i == 1 || i==2)
           return 1;
        else
            return (fib(i-1)+fib(i-2));
    }
    public Integer call()
    {
        int sum=0;
       for(int i=1 ;i<n;i++)
       {
           sum+=fib(i);
       }
       return sum;
    }
}   
