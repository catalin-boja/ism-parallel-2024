package ro.ase.ism.parallel.livelock;

public class PlayerThread extends Thread{
	String name;
	int ticketNumber;
	GameThread game;
	
	public PlayerThread(String name, int ticketNumber, GameThread game) {
		super();
		this.name = name;
		this.ticketNumber = ticketNumber;
		this.game = game;
	}

	@Override
	public void run() {
		
		System.out.println(this.name + " has the ticket " + this.ticketNumber);
		
		while(true) {
			if(game.getLuckyNumber() == this.ticketNumber) {
				System.out.println(this.name + " gets a prize *************");
				return;
			}
			
			if(game.isFinished()) {
				System.out.println(this.name + " leaves the game >>>>>>>>>>");
				return;
			}
			
		}
	}
	
	
	
}
