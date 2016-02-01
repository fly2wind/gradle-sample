package cn.thinkjoy.sample.remoting.thrift;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.remoting.support.RemoteExporter;
import com.netflix.curator.x.discovery.ServiceInstance;

import cn.thinkjoy.sample.discovery.ServiceDetails;

public class ThriftExporter extends RemoteExporter implements InitializingBean {

	private String serviceName;

	private int port = 2005;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		checkService();
		checkServiceInterface();

		this.serviceName = this.getServiceInterface().getName();
	}

	protected ServiceInstance<ServiceDetails> getInstanceForService() throws Exception {
		return ServiceInstance.<ServiceDetails> builder().name(serviceName).port(port).build();
	}

}
