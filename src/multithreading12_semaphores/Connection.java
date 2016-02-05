package multithreading12_semaphores;

/* the Semaphores are used to limit the number of threads that can be opened at the same time and are similar to 
 * locks with multiple permission 
 * When using the semaphore don't forget to put multithreaded code in the try block so in case it is interrupted the sem.release(); still gets a chance to be called in finally block;
*/

import java.util.concurrent.Semaphore;

public class Connection
{
    private static Connection instance = new Connection();
    
    private int connections = 0; // open connection counter
    private Semaphore sem = new Semaphore(10);
    
    private Connection()
    {
        
    }
    
    
    
    public static Connection getInstance() // factory method pattern 
    {
        return instance;
    }
    
    public void connect()
    {
        try
        {
            sem.acquire(); // decrease number of available uses of semaphores
            
        }
        catch (InterruptedException e1)
        {
            e1.printStackTrace();
        }
        
        try
        {
            doConnect();
        }
        finally
        {
            sem.release();
        }
    }
    
    public void doConnect()
    {
        synchronized(this)
        {
            connections ++;
            System.out.println("Current connections: " + connections);
        }
        
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        
        synchronized(this)
        {
            connections --;
        }
    }
}