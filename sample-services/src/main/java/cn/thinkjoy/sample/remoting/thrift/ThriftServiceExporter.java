package cn.thinkjoy.sample.remoting.thrift;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.SmartLifecycle;

import cn.thinkjoy.sample.discovery.ServiceDetails;

import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServerConfig;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import com.netflix.curator.x.discovery.ServiceDiscovery;

public class ThriftServiceExporter extends ThriftExporter implements InitializingBean, SmartLifecycle {

	private ServiceDiscovery<ServiceDetails> serviceDiscovery;

	private ThriftServer server;

	public ServiceDiscovery<ServiceDetails> getServiceDiscovery() {
		return serviceDiscovery;
	}

	public void setServiceDiscovery(ServiceDiscovery<ServiceDetails> serviceDiscovery) {
		this.serviceDiscovery = serviceDiscovery;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();

		server = new ThriftServer(new ThriftServiceProcessor(new ThriftCodecManager(), ImmutableList.<ThriftEventHandler> of(), this.getService()), new ThriftServerConfig().setPort(this.getPort()));
	}

	@Override
	public void start() {
		server.start();

		try {
			serviceDiscovery.registerService(getInstanceForService());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			serviceDiscovery.registerService(getInstanceForService());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.close();
		}
	}

	@Override
	public boolean isRunning() {
		return server.isRunning();
	}

	@Override
	public int getPhase() {
		return 0;
	}

	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		stop();
		callback.run();
	}

}
