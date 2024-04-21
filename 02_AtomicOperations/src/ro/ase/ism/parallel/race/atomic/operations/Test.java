package ro.ase.ism.parallel.race.atomic.operations;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		BankAccount account = new BankAccount(1000);
		Thread alice = new ClientThread(account, "Alice");
		Thread john = new ClientThread(account, "John");
		
		alice.start();		
		john.start();
		
		alice.join();
		john.join();
		
		System.out.println("The account balance is " + account.getBalance());
		
		
	}

}
