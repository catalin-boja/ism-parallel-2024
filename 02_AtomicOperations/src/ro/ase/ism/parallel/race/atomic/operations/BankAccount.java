package ro.ase.ism.parallel.race.atomic.operations;

public class BankAccount {
	
	double balance;

	public BankAccount(double balance) {
		super();
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}

	public synchronized void pay(double amount) {
		System.out.println("A payment of " + amount + " is requested");
		if(amount <= this.balance) {
			System.out.println("Payment done : " + amount);
			this.balance -= amount;
			System.out.println("Remaining balance :" + this.balance);
		}
	}
	

}
