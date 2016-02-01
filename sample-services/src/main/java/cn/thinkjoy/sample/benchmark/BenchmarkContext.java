package cn.thinkjoy.sample.benchmark;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchmarkContext {
	
	private int roundCount;

	private CyclicBarrier barrier;

	private CountDownLatch stopSignal;

	private AtomicInteger successCounter;

	private AtomicInteger failedCounter;

	private Benchmark benchmarkTask;

	public int getRoundCount() {
		return roundCount;
	}

	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}

	public CyclicBarrier getBarrier() {
		return barrier;
	}

	public void setBarrier(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	public CountDownLatch getStopSignal() {
		return stopSignal;
	}

	public void setStopSignal(CountDownLatch stopSignal) {
		this.stopSignal = stopSignal;
	}

	public AtomicInteger getSuccessCounter() {
		return successCounter;
	}

	public void setSuccessCounter(AtomicInteger successCounter) {
		this.successCounter = successCounter;
	}

	public AtomicInteger getFailedCounter() {
		return failedCounter;
	}

	public void setFailedCounter(AtomicInteger failedCounter) {
		this.failedCounter = failedCounter;
	}

	public Benchmark getBenchmarkTask() {
		return benchmarkTask;
	}

	public void setBenchmarkTask(Benchmark benchmarkTask) {
		this.benchmarkTask = benchmarkTask;
	}

}
