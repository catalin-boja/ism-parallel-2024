package ro.ase.ism.parallel.deadlock;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		Friend alice = new Friend("Alice");
		Friend bob = new Friend("Bob");
		
		Thread tAlice = new Thread(new Runnable() {		
			@Override
			public void run() {
				alice.sayHello(bob);
			}
		});
		
		Thread tBob = new Thread(new Runnable() {
			
			@Override
			public void run() {
				bob.sayHello(alice);
			}
		});
		
		tAlice.start();
		
		tBob.start();
		
		tAlice.join();
		tBob.join();
		
		System.out.println("The end of the meeting");
		
	}

}
