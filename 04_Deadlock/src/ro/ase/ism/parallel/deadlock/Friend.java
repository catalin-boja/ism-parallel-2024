package ro.ase.ism.parallel.deadlock;

public class Friend {
	
	String name;

	public Friend(String name) {
		this.name = name;
	}
	
	public synchronized void sayHello(Friend friend) {
		System.out.println(this.name + " says hello to " + friend.name);
		friend.respondToHello();
	}
	
	
	//with this sync the 2 threads will block each other
	public synchronized void respondToHello() {
		System.out.println(this.name + " responds to hello");
	}
}
