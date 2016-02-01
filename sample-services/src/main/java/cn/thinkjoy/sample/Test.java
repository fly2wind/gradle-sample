package cn.thinkjoy.sample;

import cn.thinkjoy.sample.benchmark.Benchmark;
import cn.thinkjoy.sample.benchmark.BenchmarkUtils;
import cn.thinkjoy.sample.service.UserService;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.nifty.client.NiftyClientConnector;
import com.facebook.swift.service.ThriftClientManager;
import com.google.common.net.HostAndPort;

public class Test {

	public static void main(String[] args) throws Exception {

		final ThriftClientManager clientManager = new ThriftClientManager();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 1024; i++) {
			sb.append(i);
		}

		final String s = sb.toString();

		BenchmarkUtils.testAndPrint(100, 2000, new Benchmark() {

			FramedClientConnector connector = new FramedClientConnector(HostAndPort.fromParts("127.0.0.1", 2003));
			@Override
			public Object doTask() throws Exception {
				UserService service = clientManager.createClient(connector, UserService.class).get();

				service.getUser(s);
				return null;
			}

		});

	}

}
