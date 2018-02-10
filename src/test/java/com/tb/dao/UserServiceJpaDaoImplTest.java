package com.tb.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tb.api.UserManagementService;
import com.tb.config.TestJpaEnvironmentConfig;
import com.tb.domain.User;

/**
 * Created by jt on 12/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestJpaEnvironmentConfig.class})
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private UserManagementService userService;

    @Autowired
    public void setUserService(UserManagementService userService) {
        this.userService = userService;
    }

    @Test
    public void testSaveOfUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());

    }
}
