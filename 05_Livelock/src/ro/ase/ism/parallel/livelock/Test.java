package ro.ase.ism.parallel.livelock;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		GameThread game = new GameThread();
		
		PlayerThread alice = new PlayerThread("Alice", 13, game);
		PlayerThread bob = new PlayerThread("Bob", 10000, game);
		PlayerThread john = new PlayerThread("John", 30, game);
		
		
		alice.start();
		bob.start();
		john.start();
		
		game.start();
		
		game.join();
		alice.join();
		bob.join();
		john.join();
		
		System.out.println("The simulation is over");
	}

}
