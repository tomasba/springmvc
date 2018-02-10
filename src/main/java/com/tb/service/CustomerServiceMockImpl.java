package com.tb.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tb.api.CustomerManagementService;
import com.tb.domain.Customer;
import com.tb.domain.User;

@Service("customerManagementService")
@Profile("mock")
public class CustomerServiceMockImpl extends AbstractManagementService<Customer> implements CustomerManagementService {

	//private Map<Integer, Customer> customers = new HashMap<>();
	
	public CustomerServiceMockImpl() {
		pupulateCustomers();
	}

//	@Override
//	public Integer findNextId() {
//		return customers.size()+1;
//	}

	private void pupulateCustomers() {
		Customer c1 = new Customer();
		c1.setId(findNextId());
		c1.setFirstName("Tomas");
		c1.setLastName("Tomaitis");
		c1.setAddressLineOne("Šermukšnių 12-5");
		c1.setAddressLineTwo(null);c1.setEmail("t@t.lt");
		c1.setPhoneNumber("+37060300000");
		c1.setState("CA");
		c1.setZipCode("204454");
		c1.setCity("Vilnius");
		User u1 = new User();
		u1.setEnabled(Boolean.TRUE);
		u1.setUsername("customer1 user1");
		u1.setPassword("customer1 user1");
		c1.setUser(u1);		
		getItems().put(c1.getId(), c1);
		
		Customer c2 = new Customer();
		c2.setId(findNextId());
		c2.setFirstName("Jonas");
		c2.setLastName("Jonaitis");
		c2.setAddressLineOne("Girulių 45");
		c2.setAddressLineTwo(null);c1.setEmail("j@j.lt");
		c2.setPhoneNumber("+37060300001");
		c2.setState("LA");
		c2.setZipCode("304453");
		c2.setCity(null);
		User u2 = new User();
		u2.setEnabled(Boolean.TRUE);
		u2.setUsername("customer2 user2");
		u2.setPassword("customer2 user2");
		c2.setUser(u2);		
		getItems().put(c2.getId(), c2);
	}

//	@Override
//	public Map<Integer, Customer> getItems() {
//		return customers;
//	}

}
