package ro.ase.ism.parallel.hello;

public class Test {
	
	
	public static void sayHello(int id) {
		System.out.println("Hello world " + id);
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//sequential part
		sayHello(1);
		sayHello(2);
		sayHello(3);
		sayHello(4);
		
		
		//parallel
		System.out.println("************************************");
		
		Thread t1 = new HelloThread(1);
		Thread t2 = new HelloThread(2);
		
		Thread t3 = new Thread(new HelloThreadRunnable(3));
		Thread t4 = new Thread(new HelloThreadRunnable(4));
		
//		t1.run();
//		t2.run();
//		t3.run();
//		t4.run();
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		//waiting for threads to finish
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		System.out.println("The end of the example");
	}
}
