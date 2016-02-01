package cn.thinkjoy.sample.benchmark;

import java.util.List;

public class BenchmarkResult {

	private int concurrencyLevel;
	private int totalTasks;

	private long testsTakenTime;
	private int failedTasks;

	private List<Long> allTimes;

	private List<BenchmarkThreadWorker> workers;

	public long getTestsTakenTime() {
		return testsTakenTime;
	}

	public int getConcurrencyLevel() {
		return concurrencyLevel;
	}

	public void setConcurrencyLevel(int concurrencyLevel) {
		this.concurrencyLevel = concurrencyLevel;
	}

	public int getTotalTasks() {
		return totalTasks;
	}

	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}

	public void setTestsTakenTime(long testsTakenTime) {
		this.testsTakenTime = testsTakenTime;
	}

	public int getFailedTasks() {
		return failedTasks;
	}

	public void setFailedTasks(int failedTasks) {
		this.failedTasks = failedTasks;
	}

	public List<Long> getAllTimes() {
		return allTimes;
	}

	public void setAllTimes(List<Long> allTimes) {
		this.allTimes = allTimes;
	}

	public List<BenchmarkThreadWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<BenchmarkThreadWorker> workers) {
		this.workers = workers;
	}

}
