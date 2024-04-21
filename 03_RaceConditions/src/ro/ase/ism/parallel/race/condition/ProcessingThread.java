package ro.ase.ism.parallel.race.condition;

public class ProcessingThread extends Thread{
	
	long lowerLimit;
	long upperLimit;
	long checkedValue;
	
	Counter counter;
	

	public ProcessingThread(long lowerLimit, long upperLimit, Counter counter, long value) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.counter = counter;
		this.checkedValue = value;
	}

	@Override
	public void run() {
		for(long value = lowerLimit; value <= upperLimit; value += 1) {
			if(value % checkedValue == 0) {
				counter.increment();
			}
		}
	}	
	
}
