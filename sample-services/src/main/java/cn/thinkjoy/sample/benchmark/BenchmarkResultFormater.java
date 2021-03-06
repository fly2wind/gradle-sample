package cn.thinkjoy.sample.benchmark;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class BenchmarkResultFormater {
	
	public void format(BenchmarkResult stressResult, Writer writer) {
		long testsTakenTime = stressResult.getTestsTakenTime();
		int totalTasks = stressResult.getTotalTasks();
		int concurrencyLevel = stressResult.getConcurrencyLevel();

		float takes = BenchmarkStatisticsUtils.toMs(testsTakenTime);

		List<Long> allTimes = stressResult.getAllTimes();
		long totaleTimes = BenchmarkStatisticsUtils.getTotal(allTimes);

		// float tps = (totalTasks * 1000) / takes;
		float tps = 1000 * 1000000 * (concurrencyLevel * (totalTasks / (float) totaleTimes));

		float averageTime = BenchmarkStatisticsUtils.getAverage(totaleTimes, totalTasks);

		float onTheadAverageTime = averageTime / concurrencyLevel;

		int count_50 = totalTasks / 2;
		int count_66 = totalTasks * 66 / 100;
		int count_75 = totalTasks * 75 / 100;
		int count_80 = totalTasks * 80 / 100;
		int count_90 = totalTasks * 90 / 100;
		int count_95 = totalTasks * 95 / 100;
		int count_98 = totalTasks * 98 / 100;
		int count_99 = totalTasks * 99 / 100;

		long longestTask = allTimes.get(allTimes.size() - 1);
		long shortestTask = allTimes.get(0);

		StringBuilder view = new StringBuilder();

		// if (StringUtils.isNotBlank(serviceName)) {
		// view.append(" Service Name:\t").append(serviceName);
		// view.append("\r\n");
		// }
		view.append(" Concurrency Level:\t").append(concurrencyLevel);
		view.append("\r\n Time taken for tests:\t").append(takes).append(" ms");
		view.append("\r\n Complete Tasks:\t").append(totalTasks);
		view.append("\r\n Failed Tasks:\t\t").append(stressResult.getFailedTasks());
		view.append("\r\n Tasks per second:\t").append(tps);
		view.append("\r\n Time per task:\t\t").append(BenchmarkStatisticsUtils.toMs(averageTime)).append(" ms");
		view.append("\r\n Time per task:\t\t").append(BenchmarkStatisticsUtils.toMs(onTheadAverageTime)).append(" ms (across all concurrent tasks)");
		view.append("\r\n Shortest task:\t\t").append(BenchmarkStatisticsUtils.toMs(shortestTask)).append(" ms");

		StringBuilder certainTimeView = view;
		certainTimeView.append("\r\n Percentage of the tasks served within a certain time (ms)");
		certainTimeView.append("\r\n  50%\t").append(BenchmarkStatisticsUtils.toMs(allTimes.get(count_50)));
		certainTimeView.append("\r\n  66%\t").append(BenchmarkStatisticsUtils.toMs(allTimes.get(count_66)));
		certainTimeView.append("\r\n  75%\t").append(BenchmarkStatisticsUtils.toMs(allTimes.get(count_75)));
		certainTimeView.append("\r\n  80%\t").append(BenchmarkStatisticsUtils.toMs(allTimes.get(count_80)));
		certainTimeView.append("\r\n  90%\t").append(BenchmarkStatisticsUtils.toMs(allTimes.get(count_90)));
		certainTimeView.append("\r\n  95%\t").append(BenchmarkStatisticsUtils.toMs(allTimes.get(count_95)));
		certainTimeView.append("\r\n  98%\t").append(BenchmarkStatisticsUtils.toMs(allTimes.get(count_98)));
		certainTimeView.append("\r\n  99%\t").append(BenchmarkStatisticsUtils.toMs(allTimes.get(count_99)));
		certainTimeView.append("\r\n 100%\t").append(BenchmarkStatisticsUtils.toMs(longestTask)).append(" (longest task)");

		try {
			writer.write(view.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
