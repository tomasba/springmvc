package com.tb.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tb.api.CustomerManagementService;
import com.tb.api.UserManagementService;
import com.tb.config.TestJpaEnvironmentConfig;
import com.tb.domain.Customer;
import com.tb.domain.User;


/**
 * Created by jt on 12/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestJpaEnvironmentConfig.class})
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private UserManagementService userService;
    private CustomerManagementService customerService;

    @Test
    public void testSaveOfUser() throws Exception {
    	// given
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        // when
        User savedUser = userService.saveOrUpdate(user);

        // then
        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());

    }
    
    @Test
    public void testSaveOfUserWithCustomer() throws Exception {
    	// given
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstName("Chevy");
        customer.setLastName("Chase");

        user.setCustomer(customer);
        
        // when
        User savedUser = userService.saveOrUpdate(user);

        // then
        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCustomer() != null;
        assert savedUser.getCustomer().getId() != null;

    }
    
    @Test
    public void testSaveOfUserWithCustomerAndDelete() throws Exception {
    	// given
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstName("Chevy");
        customer.setLastName("Chase");

        user.setCustomer(customer);
        
        // when
        User savedUser = userService.saveOrUpdate(user);
        userService.delete(savedUser.getId());
        
        // then
        assert userService.find(savedUser.getId()) == null;
        assert customerService.find(savedUser.getCustomer().getId()) != null;
    }    
    
    @Autowired
    public void setUserService(UserManagementService userService) {
        this.userService = userService;
    }

    @Autowired
	public void setCustomerService(CustomerManagementService customerService) {
		this.customerService = customerService;
	}

}
