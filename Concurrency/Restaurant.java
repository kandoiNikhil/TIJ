// The example does not hold a variable clean or such
// although that is one way to solve the problem
// and probably better, but I was too lazy to add another variable 
// and was still able to add so many comments line
// listen to alt-J !!!!!!!! great band 
import java.util.concurrent.*;
import java.util.*;
class Meal {
    private final int orderNumber;
    public Meal(int orderNumber) {
        this.orderNumber = orderNumber ;
    }
    public String toString() {
        return " Meal " +orderNumber ;
    }
}
class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson(Restaurant restaurant ) {
        this.restaurant = restaurant ;
    }
    public void   run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal == null )
                        wait();
                }
                System.out.println("WaitPerson got " + restaurant.meal ) ;
                synchronized(restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notify();

                }
                synchronized(restaurant.busBoy)
                {
                    restaurant.busBoy.notify();
                }
            }
        }
        catch(InterruptedException e) {
            System.out.println(" WaitPerson interrupted " );
        }
    }
}
class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant restaurant ) {
         this.restaurant = restaurant ;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal !=null )
                        wait();
                }
                if(count == 10 ) {
                    restaurant.exec.shutdownNow();
                    return;
                }
                TimeUnit.SECONDS.sleep(1);
                
                restaurant.meal = new Meal(count);
                count++;
                System.out.println(" Chef generated " + restaurant.meal );
                synchronized(restaurant.waitPerson) {
                    restaurant.waitPerson.notify();
                }
            }
        }
        catch(InterruptedException e) {
            System.out.println(" Chef interrupted ");
        }
    }
}
class BusBoy implements Runnable {
    private Restaurant restaurant;
    BusBoy(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal != null)
                        wait();
                }
                System.out.println("BusBoy cleaning" );
                TimeUnit.SECONDS.sleep(1);
            }
            }
            catch(InterruptedException e) {
                System.out.println("BusBoy interrupted");
            }
        }
    }


public class Restaurant {
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    BusBoy busBoy = new BusBoy(this);
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
   
    public static void main(String [] args) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.go();
   }
    public void go() throws Exception
    {
        exec.execute(this.busBoy);
        exec.execute(this.waitPerson);
        exec.execute(this.chef);
    }
}
