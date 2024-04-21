package ro.ase.ism.parallel.producer.consumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProducerThread extends Thread {
	
	Market market;
	
	public ProducerThread(Market market) {
		this.market = market;
	}

	@Override
	public void run() {
		while(!market.isClosed()) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Random random = new Random();
			int quantity = random.nextInt(50);
			System.out.println(">>> producing " + quantity);
			
			try {
				this.market.add(quantity);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
}
