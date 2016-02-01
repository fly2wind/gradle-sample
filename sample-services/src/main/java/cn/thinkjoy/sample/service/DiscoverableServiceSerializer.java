package cn.thinkjoy.sample.service;

import java.io.ByteArrayOutputStream;

//import com.netflix.curator.x.discovery.ServiceInstance;
//import com.netflix.curator.x.discovery.details.JsonInstanceSerializer;

/**
public class DiscoverableServiceSerializer<T> extends JsonInstanceSerializer<T> {

	public DiscoverableServiceSerializer(Class<T> payloadClass) {
		super(payloadClass);
	}
	
	public ServiceInstance<T> deserialize(byte[] bytes) throws Exception
    {
		ServiceInstance<T> r = super.deserialize(bytes);
		System.out.println("aaaa");
        return r;
    }

    @Override
    public byte[] serialize(ServiceInstance<T> instance) throws Exception
    {
    	byte[] s = super.serialize(instance);
    	System.out.println("bbb");
        return s;
    }

}
*/
