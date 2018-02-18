package com.tb.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tb.api.CustomerManagementService;
import com.tb.domain.Address;
import com.tb.domain.Customer;
import com.tb.domain.User;

@Service
//@Service("customerManagementService")
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
		c1.setPhoneNumber("+37060300000");
		Address addrC1 = new Address();
		addrC1.setAddressLineOne("Šermukšnių 12-5");
		addrC1.setAddressLineTwo(null);c1.setEmail("t@t.lt");		
		addrC1.setState("CA");
		addrC1.setZipCode("204454");
		addrC1.setCity("Vilnius");
		c1.setBillingAddress(addrC1);
		c1.setShippingAddress(addrC1);
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
		c2.setPhoneNumber("+37060300001");		
		Address addrC2 = new Address();
		addrC2.setAddressLineOne("Girulių 45");
		addrC2.setAddressLineTwo(null);c1.setEmail("j@j.lt");
		addrC2.setState("LA");
		addrC2.setZipCode("304453");
		addrC2.setCity(null);
		c2.setBillingAddress(addrC2);
		c2.setShippingAddress(addrC2);
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
