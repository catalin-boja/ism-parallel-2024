package ro.ase.ism.parallel.race.condition;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Starting single thread test...");
		
		//single thread test - aka sequentially 
		long max = (long) 1e9;
		
		Counter counter = new Counter();
		Thread thread = new ProcessingThread(1, max, counter, 3);
		
		long tStart = System.currentTimeMillis();
		thread.run();     //run it sequentially
		long tEnd = System.currentTimeMillis();
		
		System.out.println(String.format("Single thread test. %d multiples in %f seconds",
				counter.getCounter(), (tEnd - tStart)/1000.0));
		
		//same with 4 threads
		
		Counter counter2 = new Counter();
		
		List<Thread> threads = new ArrayList<>();
		
		for(int i = 0; i < 4; i++) {
			long lower = i * (max / 4) + 1;
			long upper = (i + 1) * (max / 4);
			if(i == 3) 
				upper = max;
			
			Thread t = new ProcessingThread(lower, upper, counter2, 3);
			threads.add(t);
		}
		
		tStart = System.currentTimeMillis();
		for(Thread t : threads) {
			t.start();
		}
		for(Thread t : threads) {
			t.join();
		}
		
		tEnd = System.currentTimeMillis();
		
		System.out.println(String.format("Multiple threads test. %d multiples in %f seconds",
				counter2.getCounter(), (tEnd - tStart)/1000.0));
	}

}
