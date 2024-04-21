package ro.ase.ism.parallel.producer.consumer;

import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		Market market = new Market(500);
		
		ProducerThread producer = new ProducerThread(market);
		ConsumerThread consumer = new ConsumerThread(market);
		
		producer.start();
		TimeUnit.SECONDS.sleep(2);
		consumer.start();
		
		TimeUnit.SECONDS.sleep(15);
		market.closeIt();
		
		producer.join();
		consumer.join();
		
		System.out.println("Market closed");
		
	}

}
