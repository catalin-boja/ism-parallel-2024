
#include <iostream>
#include <string>
#include <omp.h>

using namespace std;

void sayHello(int id) {
	printf("\n Hello from %d", id);
}

int main() {

	int noCores = omp_get_num_procs();
	printf("\n No cores: %d", noCores);

	//will run on the default number of threads = noCores
#pragma omp parallel 
	{
		sayHello(1);
	}

	printf("\n ********************************");

	//will run on the required number of threads
#pragma omp parallel num_threads(noCores)
	{
		sayHello(1);
	}

	printf("\n ********************************");

#pragma omp parallel num_threads(4)
	{
		int threadId = omp_get_thread_num();
		printf("\n Hello from thread %d", threadId);
		if (threadId == 0) {
			printf("\n We got %d threads for this parallel region ", omp_get_num_threads());
		}

#pragma omp master 
		{
			//executed only by the master thread = id 0
			printf("\n Hello from master thread");
		}
	}

	printf("\n ********************************");

	bool runInParallel = true;

#pragma omp parallel if(runInParallel) num_threads(2)
	{
		printf("\n Do it in parallel if we have resources");
	}

	//avoiding race conditions with atomic
	//by default result is a shared variable by all threds

	int result = 0;

#pragma omp parallel num_threads(4) shared(result)
	{
		for (int i = 0; i < 100000; i++) {
#pragma omp atomic
			result += 1;
		}
	}

	printf("\n Result is %d", result);

	//avoiding race conditions with local variables

	result = 10;


	//with private you need to init local copy
	//with firstprivate local copies take the value before the paralle region
#pragma omp parallel num_threads(4) firstprivate(result)
	{
		//result = 0;
		for (int i = 0; i < 100000; i++) {
			{
				result += 1;
			}
		}
		printf("\nThread %d local result is %d", omp_get_thread_num(), result);
	}

	printf("\n Result is %d", result);

	//doing a parallel a for loop by distributing the iterations over multiple threads

	long lower = 1;
	long upper = 50;

	int multipleCounter = 0;


#pragma omp parallel
#pragma omp for 
	for (long x = lower; x <= upper; x += 1) {

		printf("\n %d verified by thread %d", x, omp_get_thread_num());

		if (x % 3 == 0) {
#pragma omp atomic
			multipleCounter += 1;
		}
	}

	printf("\n Number of multiples = %d", multipleCounter);

	//doing a parallel a for loop by distributing the iterations over multiple threads and using a reduction

	multipleCounter = 0;

#pragma omp parallel
#pragma omp for schedule(static, 10) reduction(+: multipleCounter)
	for (long x = lower; x <= upper; x += 1) {

		//printf("\n %d verified by thread %d", x, omp_get_thread_num());

		if (x % 3 == 0) {
			multipleCounter += 1;
		}
	}

	printf("\n Number of multiples = %d", multipleCounter);
}