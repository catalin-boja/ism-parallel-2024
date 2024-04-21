
#include <iostream>
#include <thread>
#include <string>
#include <omp.h>
#include <vector>

using namespace std;

void computeMultiples(long lower, long upper, long value, int& result) {
	//int counter = 0;
	for (long x = lower; x <= upper; x += 1) {
		//just for demo
		int temp = x % value;
		if (x % value == 0) {
			result += 1;
		}
	}
	//result = counter;
}

void benchmark(string msg, void (*pf)(long, long, long, int&), long lower, long upper, long value) {
	printf("\n %s", msg.c_str());
	
	int result = 0;

	double tStart = omp_get_wtime();
	pf(lower, upper, value, result);
	double tEnd = omp_get_wtime();

	printf("\n Result %d in %f seconds", result, (tEnd - tStart));
}

//parallel solution 
void parallelSolutionWithRaceConditions(long lower, long upper, long value, int& result) {
	int noCores = omp_get_num_procs();
	printf("\n Number of cores: %d", noCores);

	vector<thread*> threads;
	for (int i = 0; i < noCores; i++) {
		long lowerLimit = i * (upper / noCores) + 1;
		long upperLimit = i == noCores - 1 ? upper : (i + 1) * (upper / noCores);



		thread* t = new thread(computeMultiples, lowerLimit, upperLimit, value, (ref)(result));
		threads.push_back(t);
	}

	for (int i = 0; i < noCores; i++) {
		threads[i]->join();
	}
}

//parallel solution without race condition and with local variables
void parallelSolutionWithLocalVariables(long lower, long upper, long value, int& result) {
	int noCores = omp_get_num_procs();
	printf("\n Number of cores: %d", noCores);

	vector<thread*> threads;
	int* results = new int[noCores];

	for (int i = 0; i < noCores; i++) {
		long lowerLimit = i * (upper / noCores) + 1;
		long upperLimit = i == noCores - 1 ? upper : (i + 1) * (upper / noCores);

		results[i] = 0;

		thread* t = new thread(computeMultiples, lowerLimit, upperLimit, value, (ref)(results[i]));
		threads.push_back(t);
	}

	for (int i = 0; i < noCores; i++) {
		threads[i]->join();
		result += results[i];
	}

}

//parallel solution without race condition and with local variables and without false cache sharing
void parallelSolutionWithLocalVariablesAndWithoutFalseCache(long lower, long upper, long value, int& result) {
	int noCores = omp_get_num_procs();
	printf("\n Number of cores: %d", noCores);

	//alignas(1024) int vb;

	vector<thread*> threads;

	//int* results = new int[noCores];
	int** results = new int*[noCores];
	for (int i = 0; i < noCores; i++) {
		results[i] = new int[100];
	}

	for (int i = 0; i < noCores; i++) {
		long lowerLimit = i * (upper / noCores) + 1;
		long upperLimit = i == noCores - 1 ? upper : (i + 1) * (upper / noCores);

		results[i][0] = 0;

		thread* t = new thread(computeMultiples, lowerLimit, upperLimit, value, (ref)(results[i][0]));
		threads.push_back(t);
	}

	for (int i = 0; i < noCores; i++) {
		threads[i]->join();
		result += results[i][0];
	}

}

int main() {
	const long max = 2e9;
	benchmark("Suquential test", computeMultiples, 1, max, 3);
	benchmark("Parallel test with race condition", parallelSolutionWithRaceConditions, 1, max, 3);
	benchmark("Parallel test without race condition and with local variables", parallelSolutionWithLocalVariables, 1, max, 3);
	benchmark("Parallel test without race condition and with local variables and without false cache", parallelSolutionWithLocalVariablesAndWithoutFalseCache, 1, max, 3);
}


