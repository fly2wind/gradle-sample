package cn.thinkjoy.sample.service;

import java.util.Collection;

/**
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.retry.ExponentialBackoffRetry;
import com.netflix.curator.x.discovery.ServiceDiscovery;
import com.netflix.curator.x.discovery.ServiceDiscoveryBuilder;
import com.netflix.curator.x.discovery.ServiceInstance;
import com.netflix.curator.x.discovery.ServiceProvider;
import com.netflix.curator.x.discovery.details.JsonInstanceSerializer;
import com.netflix.curator.x.discovery.strategies.RoundRobinStrategy;
*/
public class Client {

	public static void main(String[] args) throws Exception {
		/*
		CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));

		client.start();

		
		ServiceDiscovery<DiscoverableService> serviceDiscovery = ServiceDiscoveryBuilder.builder(DiscoverableService.class).client(client).basePath("/discovery/services").build();
		serviceDiscovery.start();

		ServiceProvider<DiscoverableService> serviceProvider = serviceDiscovery.serviceProviderBuilder().serviceName("userservice").providerStrategy(new RoundRobinStrategy<DiscoverableService>()).build();
		serviceProvider.start();
		
		ServiceInstance<DiscoverableService> serviceInstance =  serviceProvider.getInstance();
		
		System.out.println(serviceInstance);

		while (true) {
			Thread.sleep(1000);
		}
		*/

	}

}
