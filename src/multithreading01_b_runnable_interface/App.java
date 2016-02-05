package multithreading01_b_runnable_interface;

// This code shows a preferred way of opening threads by implementing a Runnable interface and passing it to Thread constructor
public class App
{

	
	public static void main(String[] args)
	{
		// Runner implements Runnable interface that is passed to Thread constructor
		Thread t1 = new Thread (new Runner());
		t1.start();
		
		Thread t2 = new Thread (new Runner());
		t2.start();

	}

}
