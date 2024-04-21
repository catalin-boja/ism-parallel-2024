package ro.ase.ism.parallel.livelock;

import java.util.Random;

public class GameThread extends Thread{

	//without volatile the other threads don't have time to see the change
	volatile int luckyNumber;
	volatile boolean isFinished = false;
	
	public int getLuckyNumber() {
		return luckyNumber;
	}
	public boolean isFinished() {
		return isFinished;
	}
	
	@Override
	public void run() {
		//generate a number of numbers and stop
		int noNumbers = 50;
		while(noNumbers > 0) {
			
			Random random = new Random();
			this.luckyNumber = random.nextInt(40) + 1;
			System.out.println("The current lucky number is " + this.luckyNumber);
			
			noNumbers -= 1;
		}
		
		this.isFinished = true;
			
		System.out.println("********* Game over *************");
	}
}
