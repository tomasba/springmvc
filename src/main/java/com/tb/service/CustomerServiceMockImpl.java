package com.tb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tb.domain.Customer;

@Service
public class CustomerServiceMockImpl implements CustomerService {

	private Map<Integer, Customer> customers = new HashMap<>();
	
	public CustomerServiceMockImpl() {
		pupulateCustomers();
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		if (customer != null && customer.getId() == null) {
			customer.setId(findNextId());	
		}		
		customers.put(customer.getId(), customer);
		return customer;
	}

	private Integer findNextId() {
		return customers.size()+1;
	}

	@Override
	public List<Customer> findAllCustomers() {
		return new ArrayList<>(customers.values());
	}

	@Override
	public Customer findCustomer(Integer id) {
		return customers.get(id);
	}

	@Override
	public void deleteCustomer(Integer id) {
		customers.remove(id);
	}

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
		customers.put(c1.getId(), c1);
		
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
		customers.put(c2.getId(), c2);
	}	
}
