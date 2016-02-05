package multithreading03_synchronized_method;


/* 
 * This code shows the usage of synchronized method
 * the use of synchronized keyword in front of the method ensures that the thread running a method
 * obtains a lock on the object to which the method belongs to
 * At the end of execution of synchronized method the lock is released
 * While a thread has a lock on the object no other thread can run synchronized methods that belong to that object 
 * In this way the use of word synchronized ensures that memory values that all threads access for both reads and writes are synchronized
 * and correct
 * 
 */
public class App
{

	private int count = 0;

	public static void main(String[] args)
	{
		App app = new App();
		app.doWork();
	}

	// when increment is called the thread running it puts a lock on app object and releases it
	// when the method finishes execution. In this way the value of count is accessed by one thread at a time
	// and the variable is incremented correctly
	public synchronized void increment() 
	{
		count++;
	}
	
	//!!! This is an example of incorrect logic the method decrementing count is not sychronized and when thread 1 calls increment
	// simultaneously with thread two calling decrement the value of count is not changed correctly.
	public void decrement()
	{
		count--;
	}

	public void doWork()
	{
		Thread t1 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				for (int i = 0; i < 10000; i++)
					increment();

			}
		});

		Thread t2 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				for (int i = 0; i < 10000; i++)
				{
					increment();
					decrement();
				}
			}
		});

		t1.start();
		t2.start();

		try
		{
			t1.join(); // main thread waits for t1 thread to complete until continuing execution
			t2.join(); // main thread waits for t2 thread to complete until continuing execution
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Count is " + count);
	}

}
