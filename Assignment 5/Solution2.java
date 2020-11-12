public class Solution2 extends Thread
{
	int startNumberOfthread;
	static int maxDivisorCount=0, numWithMaxDivisorCount=0;
	
	public Solution2(int startNumberOfThread)
	{
		this.startNumberOfthread = startNumberOfThread;
	}
	
	public static void main(String[] args) 
	{
		Solution2 t1 = new Solution2(1);
		Solution2 t2 = new Solution2(10001);
		Solution2 t3 = new Solution2(20001);
		Solution2 t4 = new Solution2(30001);
		Solution2 t5 = new Solution2(40001);
		Solution2 t6 = new Solution2(50001);
		Solution2 t7 = new Solution2(60001);
		Solution2 t8 = new Solution2(70001);
		Solution2 t9 = new Solution2(80001);
		Solution2 t10 = new Solution2(90001);
		
		long startTime = System.currentTimeMillis();
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		
		try
		{
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
		}
		catch(Exception e) {}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(numWithMaxDivisorCount + " has the maximum number of divisors i.e " + maxDivisorCount);
		System.out.println("Total time elapsed: " + (endTime - startTime) + "ms");		
	}
	
	public void run()
	{
		for(int i=this.startNumberOfthread; i<this.startNumberOfthread+10000; i++)
		{
			int numOfDivisors = 0;
			for(int j=1; j<=i; j++)
				if(i%j == 0) numOfDivisors++;
				
			if(numOfDivisors > maxDivisorCount)
			{
				synchronized(this)
				{
					maxDivisorCount = numOfDivisors;
					numWithMaxDivisorCount = i;
				}
			}
		}
	}

}
