
#include <iostream>
#include <thread>

using namespace std;

void sayHello(int id) {
	printf("\nHello from %d", id);
}

void increment(int& value) {
	value += 1;
}

void add(int a, int b, int& result) {
	result = a + b;
}

int main() {

	std::thread tHello1(sayHello, 1);
	std::thread tHello2(sayHello, 2);
	std::thread tHello3(sayHello, 3);
	std::thread tHello4(sayHello, 4);

	tHello1.join();
	tHello2.join();
	tHello3.join();
	tHello4.join();

	int vb = 10;
	//adition it on a different thread

	int result = 0;
	thread tAdd(add, 10, 20, (ref) (result));

	tAdd.join();

	printf("\n Result is %d", result);

	printf("\n The end");

}