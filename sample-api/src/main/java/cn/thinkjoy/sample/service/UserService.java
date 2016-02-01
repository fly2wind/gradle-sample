package cn.thinkjoy.sample.service;

import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

@ThriftService
public interface UserService {

	@ThriftMethod
	public String getUser(String id);
}
