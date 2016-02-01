package cn.thinkjoy.sample.service;

//import com.netflix.curator.framework.CuratorFramework;
//import com.netflix.curator.framework.CuratorFrameworkFactory;
//import com.netflix.curator.retry.ExponentialBackoffRetry;
//import com.netflix.curator.x.discovery.ServiceDiscovery;
//import com.netflix.curator.x.discovery.ServiceDiscoveryBuilder;
//import com.netflix.curator.x.discovery.ServiceInstance;
//import com.netflix.curator.x.discovery.strategies.RoundRobinStrategy;

public class Main {

	public static void main(String[] args) throws Exception {
		//CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(Integer.MAX_VALUE, 3));

		//client.start();

		//ServiceInstance<DiscoverableService> serviceInstance = ServiceInstance.<DiscoverableService> builder().name("userservice").port(9898).build();

		//ServiceDiscovery<DiscoverableService> serviceDiscovery = ServiceDiscoveryBuilder.builder(DiscoverableService.class).client(client).basePath("/discovery/services").build();
		//serviceDiscovery.start();

		//serviceDiscovery.registerService(serviceInstance);
		
		while (true) {
			Thread.sleep(1000);
		}

	}

}
