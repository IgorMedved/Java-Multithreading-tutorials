package multithreading10_reentrant_locks;


// This code shows how to use reentrant locks
// The reentrant lock is a special class for locking the threads. Unlike the synchronized statements or methods
// the ReentrantLocks do not release its lock at the end of the current block or method
// The advantage is that the lock can be released in other methods
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
