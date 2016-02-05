package multithreading10_reentrant_locks;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.Scanner;

// advantage of reentrant keylocks is that you can put lock in one function, but unlock it in another

public class Runner
{
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
    
    private void increment()
    {
        for (int i = 0; i < 10000; i++)
        {
            count++;
        }
    }
    
    public void firstThread() throws InterruptedException
    {
        System.out.println("First thread running");
        lock.lock(); // lock is obtained
        
        System.out.println("Waiting...");
        cond.await(); // execution stopped the lock is released until signal is called from another thread()
        System.out.println("Woken up!!!");
        
        try
        {
            increment();
        }
        finally
        {
            lock.unlock(); // don't forget to release the lock
        }
        
        
    }
    
    public void secondThread() throws InterruptedException
    {
        System.out.println("Second thread running");
        
        Thread.sleep(1000);
        lock.lock();
        
        System.out.println("Press return key... ");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");
        
        cond.signal(); // the thread notifies other thread that it can continue execution when it can obtain a lock
        try
        {
            increment();
        }
        finally
        {
            lock.unlock(); // lock released now other thread can continue its execution
        }
       
    }
    
    public void finished()
    {
        System.out.println("Count is " + count);
    }
}
