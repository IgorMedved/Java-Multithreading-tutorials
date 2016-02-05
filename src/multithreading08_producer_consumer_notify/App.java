
package multithreading08_producer_consumer_notify;
// this code shows how to use wait() and notify() methods that every class has
// wait function stops thread execution and also releases the lock on the synchronized method or statement
// the notify() method lets all other thread know that they can continue, however notify does not release a lock 
// on a thread where it is executing
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
