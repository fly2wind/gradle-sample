package cn.thinkjoy.sample.discovery;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.x.discovery.ServiceDiscovery;
import com.netflix.curator.x.discovery.ServiceDiscoveryBuilder;

public class ServiceDiscoveryFactoryBean implements FactoryBean<ServiceDiscovery<ServiceDetails>>, InitializingBean, DisposableBean {

	protected ServiceDiscovery<ServiceDetails> discovery;

	private CuratorFramework client;

	public CuratorFramework getClient() {
		return client;
	}

	public void setClient(CuratorFramework client) {
		this.client = client;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (client == null) {
			throw new IllegalArgumentException("Property 'client' is required");
		}

		discovery = ServiceDiscoveryBuilder.builder(ServiceDetails.class).client(client).basePath("/discovery/services").build();
		discovery.start();
	}

	@Override
	public void destroy() throws Exception {
		try {
			if (discovery != null) {
				discovery.close();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ServiceDiscovery<ServiceDetails> getObject() throws Exception {
		return discovery;
	}

	@Override
	public Class<?> getObjectType() {
		return ServiceDiscovery.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
