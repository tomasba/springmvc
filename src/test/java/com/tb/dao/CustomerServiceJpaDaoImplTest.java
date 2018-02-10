package com.tb.dao;

import java.util.List;

import javax.persistence.RollbackException;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestJpaEnvironmentConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {

    private CustomerManagementService customerService;
    
    // injected for detached/managed entity testing
    private UserManagementService userService;

    @Test
    public void testList() throws Exception {
    	// when
        List<Customer> customers = (List<Customer>) customerService.findAll();

        // then
        assert customers.size() == 2;
    }

    @Test(expected=RollbackException.class)
    public void testSaveCustomerWithdetachedUser() {
    	// given
        Customer customer = new Customer();
        User user = new User();
        user.setUsername("This is my user name");
        user.setPassword("MyAwesomePassword");
        customer.setUser(user);

        // when
        Customer savedCustomer = customerService.saveOrUpdate(customer);

        // then
        assert savedCustomer.getUser().getId() != null;
    }
    
    @Test
    public void testSaveCustomerManagedUser() {
    	//given
        Customer customer = new Customer();
        User user = new User();
        user.setUsername("This is my user name");
        user.setPassword("MyAwesomePassword");
        user = userService.saveOrUpdate(user);
        customer.setUser(user);

        // when
        Customer savedCustomer = customerService.saveOrUpdate(customer);

        // then
        assert savedCustomer.getUser().getId() != null;
        customerService.delete(savedCustomer.getId());
        assert customerService.find(savedCustomer.getId()) == null;
        assert userService.find(user.getId()) != null;        
    }

    @Autowired
    public void setCustomerService(CustomerManagementService customerService) {
        this.customerService = customerService;
    }    
    
    @Autowired
	public void setUserService(UserManagementService userService) {
		this.userService = userService;
	}

}