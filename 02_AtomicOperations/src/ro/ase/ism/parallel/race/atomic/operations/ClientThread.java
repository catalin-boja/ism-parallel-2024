package ro.ase.ism.parallel.race.atomic.operations;

import java.util.Random;

public class ClientThread extends Thread {
	
	BankAccount account;
	String name;
	
	public ClientThread(BankAccount account, String name) {
		this.account = account;
		this.name = name;
	}

	@Override
	public void run() {	
		while(true) {
			
			if(this.account.getBalance() <= 0) {
				return;
			}
			
			Random random = new Random();
			int value = random.nextInt(10);
			System.out.println(this.name + " wants to pay " + value);
			this.account.pay(value);
		}	
	}
}
