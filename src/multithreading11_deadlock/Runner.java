package multithreading11_deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.Random;

public class Runner
{
    private Account acc1 = new Account();
    private Account acc2 = new Account();
    
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    
    
    private void acquireLocks (Lock firstLock, Lock secondLock) throws InterruptedException
    {
        while (true)
        {
        
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;
            
            
            // try to acquire locks
            try
            {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            }
            finally
            {
                // if both locks acquired return to calling method and do the required work
            	if (gotFirstLock && gotSecondLock)
                    return;
                
                // if only firstLock is acquired release it to prevent deadlock
            	if (gotFirstLock)
                    firstLock.unlock();
                    
            	// if only secondLock is acquired release it to prevent deadlock
                if (gotSecondLock)
                    secondLock.unlock();
            }
            
            // locks not acquired wait 1ms to let other thread do the work
            
            Thread.sleep(1);
        }
        
        
    }
    
    public void firstThread() throws InterruptedException
    {
        Random random = new Random();
        
        for (int i = 0; i < 1000; i++)
        {
            
        	// To prevent deadlock instead of calling lock1.lock() and lock2.lock() the acquire lock function is called
        	acquireLocks(lock1, lock2); 
            try
            {
                Account.transfer(acc1, acc2, random.nextInt(100));
            }
            finally
            {
                lock1.unlock();
                lock2.unlock();
            }
            
        }
            
    }
    
    public void secondThread() throws InterruptedException
    {
        Random random = new Random();
        
        for (int i = 0; i < 1000; i++)
        {
        	// To prevent deadlock instead of calling lock1.lock() and lock2.lock() the acquire lock function is called
        	acquireLocks(lock2, lock1);
            try
            {
                Account.transfer(acc2, acc1, random.nextInt(100));
            }
            finally
            {
                lock1.unlock();
                lock2.unlock();
            }
        }
       
    }
    
    public void finished()
    {
        System.out.println("Account 1 balance is: " + acc1.getBalance());
        System.out.println("Account 2 balance is: " + acc2.getBalance());
        System.out.println("Total balance is: " + (acc1.getBalance()+ acc2.getBalance()));
    }
}