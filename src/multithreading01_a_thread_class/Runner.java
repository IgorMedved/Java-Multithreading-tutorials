package multithreading01_a_thread_class;

/* Running a thread by extending a Thread class
 * While it is possible to run threads this way the preferable way involves implementing Runnable interface as seen in the next
 * example
 */
public class Runner extends Thread
{
	@Override
	// this code will run in the background thread
	// make sure that start() method of Runner class is called or the code will run in main thread
	public void run()
	{
		for (int i = 0; i < 10; i ++)
		{
			System.out.println("Hello " + i);
			
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
