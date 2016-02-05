package multithreading09_producer_consumer_sync;


// this code shows the use of wait and notify with a specifically created object
// it works in the same manner as the previous code
public class App
{

	public static void main(String[] args) throws InterruptedException
	{
		final Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable(){
			
			@Override
			public void run()
			{
				try
				{
					processor.produce();
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){
			
			@Override
			public void run()
			{
				try
				{
					processor.consume();
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}

}