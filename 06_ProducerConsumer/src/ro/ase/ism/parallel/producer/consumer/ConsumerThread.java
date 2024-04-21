package ro.ase.ism.parallel.producer.consumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ConsumerThread extends Thread {
	
	Market market;
	
	public ConsumerThread(Market market) {
		this.market = market;
	}

	@Override
	public void run() {
		while(!market.isClosed()) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Random random = new Random();
			int quantity = random.nextInt(50);
			System.out.println("<<< consuming " + quantity);
			
			try {
				this.market.get(quantity);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
}
