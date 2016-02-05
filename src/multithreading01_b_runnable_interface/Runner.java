package multithreading01_b_runnable_interface;

public class Runner implements Runnable
{
	// this code will run in background thread
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
