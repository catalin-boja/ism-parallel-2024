package ro.ase.ism.parallel.producer.consumer;

import java.util.concurrent.PriorityBlockingQueue;

public class Market {
	
	
	int stock;
	int limit;
	volatile boolean isClosed;
	
	public Market(int limit) {
		super();
		this.limit = limit;
	}

	public boolean isClosed() {
		return isClosed;
	}
	
	public void closeIt() {
		this.isClosed = true;
	}
	
	public synchronized void add(int quantity) throws InterruptedException {
		
		this.notify();
		
		if(this.stock + quantity > this.limit) {
			System.out.println("The stock will pass its limit. Stop the producer");
			this.wait();
		}
		else {
			this.stock += quantity;
			System.out.println("Stock has been increased. New value is " + this.stock);
		}
	}
	
	
	public synchronized void get(int quantity) throws InterruptedException {
		
		this.notifyAll();
		
		if(this.stock - quantity < 0) {
			System.out.println("The stock is depleted. Stop the consumer");
			this.wait();
		} else {
			this.stock -= quantity;
			System.out.println("Stock has been decreased. New value is " + this.stock);
		}
	}
	
}
