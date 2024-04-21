package ro.ase.ism.parallel.hello;

public class HelloThread extends Thread{
	
	int id;
	
	public HelloThread(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Hello world from thread " + id);
	}

}
