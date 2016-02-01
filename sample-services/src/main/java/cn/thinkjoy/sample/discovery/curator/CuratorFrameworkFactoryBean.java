package cn.thinkjoy.sample.discovery.curator;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.framework.CuratorFrameworkFactory.Builder;
import com.netflix.curator.retry.RetryNTimes;

public class CuratorFrameworkFactoryBean implements FactoryBean<CuratorFramework>, InitializingBean, DisposableBean {

	private CuratorFramework client;

	private String address;

	private String username;

	private String password;
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Builder builder = CuratorFrameworkFactory.builder();

		builder.connectString(address).retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000));

		if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
			builder.authorization("digest", new StringBuffer().append(username).append(":").append(password).toString().getBytes());
		}

		client = builder.build();
		client.start();
	}

	@Override
	public void destroy() throws Exception {
		try {
			if (client != null) {
				client.close();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CuratorFramework getObject() throws Exception {
		return client;
	}

	@Override
	public Class<?> getObjectType() {
		return CuratorFramework.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
