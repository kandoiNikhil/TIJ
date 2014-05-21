import java.util.*;
import java.util.concurrent.*;
enum Status { THINKING , EATING };  
class Chopstick {
    private int id;
    private boolean taken  = false ;
    Chopstick (int id) {
        this.id = id ;
    }
    public boolean get_status() {
        return taken ;
    }
    public void synchronized take() throws InterruptedException {
        while(taken)
            wait();
        taken = true;
    }
    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
class Bowl {
    Chopstick[] chopsticks;
    Bowl(Chopstick[] chopsticks) {
        this.chopsticks = chopsticks;
    }
    public synchronized void takeChopsticks() {
        
    }
}
class Philospher {
    private String name;
    private Status status = Status.THINKING;
    private Bowl bowl;
    Philospher (String name, Bowl bowl) {
       this.name = name;
       this.bowl = bowl;
    }
    public void run() {

    }
} 
    
