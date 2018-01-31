package com.tb.service;

import java.util.List;

import com.tb.domain.Customer;

public interface CustomerService {

	Customer saveOrUpdateCustomer(Customer customer);
	
	List<Customer> findAllCustomers();

	Customer findCustomer(Integer id);
	
	void deleteCustomer(Integer id);	
	
}
