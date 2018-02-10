package com.tb.dao;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tb.api.UserManagementService;
import com.tb.domain.User;

@Service
//@Service("userService")
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoImpl<User> implements UserManagementService {

	private StrongPasswordEncryptor strongEncryptor;

	@Override
	public User saveOrUpdate(User item) {
		// usually DAO should not contain additional specific logic. it should be responsibility of service using the DAO impl.
		if (item.getPassword() != null) {
			item.setEncryptedPassword(strongEncryptor.encryptPassword(item.getPassword()));
		}
		return super.saveOrUpdate(item);
	}	
	
	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Autowired
	public void setStrongEncryptor(StrongPasswordEncryptor strongEncryptor) {
		this.strongEncryptor = strongEncryptor;
	}	
}