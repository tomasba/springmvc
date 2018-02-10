package com.tb.service;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tb.api.UserManagementService;
import com.tb.domain.User;

@Service("userService")
@Profile("mock")
public class UserServiceMockImpl extends AbstractManagementService<User> implements UserManagementService {

	private Map<Integer, User> users;

	@Override
	public Map<Integer, User> getItems() {
		return null;
	}

	@Override
	public Integer findNextId() {
		return null;
	}
}
