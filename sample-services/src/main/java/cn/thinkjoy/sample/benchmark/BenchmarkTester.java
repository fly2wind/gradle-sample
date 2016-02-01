package cn.thinkjoy.sample.benchmark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchmarkTester {
	private int defaultWarmUpTime = 1700;

	private Benchmark EMPTY_BENCHMARK = new Benchmark() {
		@Override
		public Object doTask() throws Exception {
			// ignore
			return null;
		}

	};

	static {
		warnSelf();
	}

	protected static void warnSelf() {
		for (int i = 0; i < 50; i++) {
			BenchmarkTester benchmark = new BenchmarkTester();
			benchmark.test(10, 100, null, 0);
		}
	}

	// warm up
	protected void warmUp(int warmUpTime, Benchmark stressTask) {
		for (int i = 0; i < warmUpTime; i++) {
			try {
				stressTask.doTask();
				// benchmarkWorker.doRun();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public BenchmarkResult test(int concurrencyLevel, int totalTasks, Benchmark stressTask) {
		return test(concurrencyLevel, totalTasks, stressTask, defaultWarmUpTime);
	}

	public BenchmarkResult test(int concurrencyLevel, int totalTasks, Benchmark benchmark, int warmUpTime) {

		if (null == benchmark) {
			benchmark = EMPTY_BENCHMARK;
		}
		
		warmUp(warmUpTime, benchmark);
		
		int roundCount = totalTasks / concurrencyLevel;
		
		BenchmarkContext context = new BenchmarkContext();
		context.setRoundCount(roundCount);
		context.setBarrier(new CyclicBarrier(concurrencyLevel));
		context.setStopSignal(new CountDownLatch(concurrencyLevel));
		context.setFailedCounter(new AtomicInteger());
		context.setBenchmarkTask(benchmark);

		ExecutorService executorService = Executors.newFixedThreadPool(concurrencyLevel);

		List<BenchmarkThreadWorker> workers = new ArrayList<BenchmarkThreadWorker>(concurrencyLevel);
		for (int i = 0; i < concurrencyLevel; i++) {
			BenchmarkThreadWorker worker = new BenchmarkThreadWorker(context, roundCount);
			workers.add(worker);
		}

		for (int i = 0; i < concurrencyLevel; i++) {
			BenchmarkThreadWorker worker = workers.get(i);
			executorService.submit(worker);
		}

		try {
			context.getStopSignal().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executorService.shutdownNow();

		int realTotalTasks = roundCount * concurrencyLevel;
		
		int failedTasks = context.getFailedCounter().get();
		
		
		BenchmarkResult result = new BenchmarkResult();

		SortResult sortResult = getSortedTimes(workers);
		List<Long> allTimes = sortResult.allTimes;

		result.setAllTimes(allTimes);
		List<Long> trheadTimes = sortResult.trheadTimes;
		long totalTime = trheadTimes.get(trheadTimes.size() - 1);

		result.setTestsTakenTime(totalTime);
		result.setFailedTasks(failedTasks);
		result.setTotalTasks(realTotalTasks);
		result.setConcurrencyLevel(concurrencyLevel);
		result.setWorkers(workers);

		return result;

	}

	protected SortResult getSortedTimes(List<BenchmarkThreadWorker> workers) {
		List<Long> allTimes = new ArrayList<Long>();
		List<Long> trheadTimes = new ArrayList<Long>();
		for (BenchmarkThreadWorker worker : workers) {
			List<Long> everyWorkerTimes = worker.getEveryTimes();

			long workerTotalTime = BenchmarkStatisticsUtils.getTotal(everyWorkerTimes);
			trheadTimes.add(workerTotalTime);

			for (Long time : everyWorkerTimes) {
				allTimes.add(time);
			}
		}
		Collections.sort(allTimes);
		Collections.sort(trheadTimes);
		SortResult result = new SortResult();
		result.allTimes = allTimes;
		result.trheadTimes = trheadTimes;
		return result;
	}

	class SortResult {
		List<Long> allTimes;
		List<Long> trheadTimes;
	}
}
