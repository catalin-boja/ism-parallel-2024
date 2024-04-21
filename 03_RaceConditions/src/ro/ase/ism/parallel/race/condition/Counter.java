package ro.ase.ism.parallel.race.condition;

public class Counter {
	int value = 0;
	
	public synchronized void increment() {
		this.value += 1;
	}
	
	public int getCounter() {
		return this.value;
	}
}
