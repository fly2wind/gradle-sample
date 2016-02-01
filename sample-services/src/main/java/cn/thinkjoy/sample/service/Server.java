package cn.thinkjoy.sample.service;
/**
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftService;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Uninterruptibles;

public class Server {

	public class SimpleService implements EchoService {

		public void sleep(int seconds) {
			Uninterruptibles.sleepUninterruptibly(seconds, TimeUnit.SECONDS);
		}
	}
	
	@ThriftService
	public interface EchoService {
		@ThriftMethod
		public void sleep(int seconds);
	}

	public void run() {
		NettyServerConfig serverConfig = NettyServerConfig.newBuilder().setBossThreadExecutor(Executors.newCachedThreadPool()).setWorkerThreadExecutor(Executors.newCachedThreadPool()).build();

		
		///1111
		ThriftServiceProcessor processor1 = new ThriftServiceProcessor(new ThriftCodecManager(), ImmutableList.<ThriftEventHandler> of(), new SimpleService());

		ThriftServerDef serverDef = ThriftServerDef.newBuilder().withProcessor(processor1).listen(9898).build();
		
		
		
		

		ThriftServer server = new ThriftServer(serverConfig, serverDef);

	}

	public static void main(String[] args) {
		new Server().run();
	}

}
*/
