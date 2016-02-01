package cn.thinkjoy.sample.remoting.thrift;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.remoting.support.RemoteAccessor;

import cn.thinkjoy.sample.discovery.ServiceDetails;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;
import com.google.common.net.HostAndPort;
import com.netflix.curator.x.discovery.ServiceDiscovery;
import com.netflix.curator.x.discovery.ServiceInstance;
import com.netflix.curator.x.discovery.ServiceProvider;
import com.netflix.curator.x.discovery.strategies.RoundRobinStrategy;

public class ThriftClientInterceptor extends RemoteAccessor implements InitializingBean, MethodInterceptor {

	private ServiceDiscovery<ServiceDetails> serviceDiscovery;

	private ServiceProvider<ServiceDetails> serviceProvider;
	
	private ServiceInstance<ServiceDetails> serviceInstance;
	
	private ThriftClientManager clientManager;
	
	public ServiceDiscovery<ServiceDetails> getServiceDiscovery() {
		return serviceDiscovery;
	}

	public void setServiceDiscovery(ServiceDiscovery<ServiceDetails> serviceDiscovery) {
		this.serviceDiscovery = serviceDiscovery;
	}

	@Override
	public void afterPropertiesSet() {
		try {
			serviceProvider = serviceDiscovery.serviceProviderBuilder().serviceName(this.getServiceInterface().getName()).providerStrategy(new RoundRobinStrategy<ServiceDetails>()).build();
			serviceProvider.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		clientManager = new ThriftClientManager();
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		
		//get the serviceInstance from zookeeper
		serviceInstance = serviceProvider.getInstance();
		
		//System.out.println(serviceInstance);
		
		//rpc call
		Object service = clientManager.createClient(new FramedClientConnector(HostAndPort.fromParts("127.0.0.1", serviceInstance.getPort())), this.getServiceInterface()).get();
		
		return method.invoke(service, invocation.getArguments());

	}

}
