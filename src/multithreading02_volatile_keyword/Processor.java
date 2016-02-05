package multithreading02_volatile_keyword;

public class Processor extends Thread
{
	 private volatile boolean running = true; // correct usage of volatile as a shutdown flag
	    
	 	@Override
	    public void run()
	    {
	        // running is initialized as true and stays that way until shutdown method is called from main thread
	 		// running is volatile to ensure that the change made to running by calling shutdown() on the main thread
	 		// is read synchronized and the change is passed to background thread where run() is executed
	 		while (running)
	        {
	            System.out.println ("Hello");
	            
	            try
	            {
	                Thread.sleep(300);
	            }
	            catch (InterruptedException e)
	            {
	                e.printStackTrace();
	            }
	        }
	    }
	    public void shutdown()
	    {
	        running = false;
	    }
	
}
