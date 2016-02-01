package cn.thinkjoy.sample.benchmark;

import java.io.StringWriter;

public class BenchmarkUtils {
	private static BenchmarkTester stressTester = new BenchmarkTester();

	private static BenchmarkResultFormater simpleResultFormater = new BenchmarkResultFormater();

	public static BenchmarkResult test(int concurrencyLevel, int totalTasks, Benchmark stressTask) {
		return stressTester.test(concurrencyLevel, totalTasks, stressTask);
	}

	public static BenchmarkResult test(int concurrencyLevel, int totalTasks, Benchmark stressTask, int warmUpTime) {
		return stressTester.test(concurrencyLevel, totalTasks, stressTask, warmUpTime);
	}

	public static void testAndPrint(int concurrencyLevel, int totalTasks, Benchmark stressTask) {
		testAndPrint(concurrencyLevel, totalTasks, stressTask, null);
	}

	public static void testAndPrint(int concurrencyLevel, int totalTasks, Benchmark stressTask, String testName) {
		BenchmarkResult stressResult = test(concurrencyLevel, totalTasks, stressTask);
		String str = format(stressResult);
		System.out.println(str);
	}

	public static void testAndPrint(int concurrencyLevel, int totalTasks, Benchmark stressTask, int warmUpTime, String testName) {
		BenchmarkResult stressResult = test(concurrencyLevel, totalTasks, stressTask, warmUpTime);
		String str = format(stressResult);
		System.out.println(" " + testName + " test result:");
		System.out.println(str);
	}

	public static String format(BenchmarkResult stressResult) {
		return format(stressResult, simpleResultFormater);
	}

	public static String format(BenchmarkResult stressResult, BenchmarkResultFormater stressResultFormater) {
		StringWriter sw = new StringWriter();
		stressResultFormater.format(stressResult, sw);
		return sw.toString();
	}
}
