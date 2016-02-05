package multithreading11_deadlock;


/*
 * The deadlock is a condition that can happen with two or more locks involved.
 * If thread one has obtained a lock1, but in order to continue needs to obtain lock2
 * While thread two has obtained lock2, but needs to obtain lock1 in order to continue
 * The locks are released only at the end of each synchronized statements or method, thus neither thread can continue 
 * its execution waiting for the other thread to release the lock
 * 
 * The Runner class shows a solution to deadlock. See it for more details
 * 
 */
public class App{

    public static void main(String []args) throws InterruptedException
    {
       
       final Runner runner = new Runner();
       
       Thread t1 = new Thread(new Runnable(){
           
           @Override
           public void run()
           {
               try
               {
                   runner.firstThread();
               }
               catch (InterruptedException e)
               {
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
                   runner.secondThread();
               }
               catch (InterruptedException e)
               {
                   e.printStackTrace();
               }
           }
       
           
       });
       
       t1.start();
       t2.start();
       t1.join();
       t2.join();
       
       runner.finished();
    }
}