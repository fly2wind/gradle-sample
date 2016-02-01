package cn.thinkjoy.sample.service;

import cn.thinkjoy.sample.protocol.User;

public class UserServiceImpl implements UserService {

	@Override
	public String getUser(String id) {
		return id;
	}

}
