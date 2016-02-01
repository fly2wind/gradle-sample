/* 
 * Copyright (c) 2014 By Shawn Ma. All rights reserved.
 */
package cn.thinkjoy.sample.remoting.thrift;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * ThriftProxyFactoryBean.
 * 
 * @project sample-services
 * @date 2014-3-12
 * @version 1.0.0
 * @author qma
 */
public class ThriftProxyFactoryBean extends ThriftClientInterceptor implements FactoryBean<Object> {

	/** The service proxy. */
	private Object serviceProxy;

	/* (non-Javadoc)
	 * @see cn.thinkjoy.sample.remoting.thrift.ThriftClientInterceptor#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		
		if (getServiceInterface() == null) {
			throw new IllegalArgumentException("Property 'serviceInterface' is required");
		}
		this.serviceProxy = new ProxyFactory(getServiceInterface(), this).getProxy(getBeanClassLoader());
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public Object getObject() throws Exception {
		return this.serviceProxy;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return getServiceInterface();
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

}
