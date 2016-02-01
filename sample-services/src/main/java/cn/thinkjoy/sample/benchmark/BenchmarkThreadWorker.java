package cn.thinkjoy.sample.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchmarkThreadWorker implements Runnable {
	
	private Benchmark service;
	
	
	private CyclicBarrier barrier;
	
	private CountDownLatch startSignal;
	
	
	private CountDownLatch stopSignal;
	
	
	private AtomicInteger successCounter = null;
	
	private AtomicInteger failedCounter = null;
	
	
	private int count;
	
	private List<Long> everyTimes;

	public BenchmarkThreadWorker(BenchmarkContext context, int count) {
		super();
		this.barrier = context.getBarrier();
		
		
		this.stopSignal = context.getStopSignal();
		
		this.failedCounter = context.getFailedCounter();
		
		this.count = count;

		everyTimes = new ArrayList<Long>(count);

		this.service = context.getBenchmarkTask();
	}

	public List<Long> getEveryTimes() {
		return everyTimes;
	}

	@Override
	public void run() {
		try {
			barrier.await();
			
			for (int i = 0; i < count; i++) {
				long start = System.nanoTime();
				try {
					service.doTask();
				} catch (Throwable e) {
					failedCounter.incrementAndGet();
				} finally {
					long stop = System.nanoTime();
					
					long limit = stop - start;
					everyTimes.add(limit);
				}
			}
			stopSignal.countDown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
