public class Solution1 extends Thread
{
	public static void main(String[] args) 
	{
		Solution1 thread1 = new Solution1();
		thread1.start();
	}
	
	public void run()
	{
		System.out.println("Thread is running. Started the count...");
		for(int i=1; i<=100; i++)
		{
			System.out.println(i);
			if(i%10 == 0) System.out.println("Counted till " + i);
			
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException ie) {}
		}
	}
}
