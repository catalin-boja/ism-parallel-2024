package ro.ase.ism.parallel.hello;

public class HelloThreadRunnable implements Runnable{
	
	int id;

	public HelloThreadRunnable(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Hello world from thread " + id);
	}

}
