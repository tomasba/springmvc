package com.tb.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tb.api.CustomerManagementService;
import com.tb.config.TestJpaEnvironmentConfig;
import com.tb.domain.Customer;
import com.tb.domain.User;

/**
 * Created by jt on 12/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestJpaEnvironmentConfig.class)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {

    private CustomerManagementService customerService;

    @Autowired
    public void setCustomerService(CustomerManagementService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testList() throws Exception {
        List<Customer> customers = (List<Customer>) customerService.findAll();

        assert customers.size() == 2;

    }

    @Test
    public void testSaveWithUser() {

        Customer customer = new Customer();
        User user = new User();
        user.setUsername("This is my user name");
        user.setPassword("MyAwesomePassword");
        customer.setUser(user);

        Customer savedCustomer = customerService.saveOrUpdate(customer);

        assert savedCustomer.getUser().getId() != null;
    }
}
