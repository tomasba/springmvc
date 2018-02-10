package com.tb.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tb.api.CustomerManagementService;
import com.tb.domain.Customer;

@Service
//@Service("customerManagementService")
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoImpl<Customer> implements CustomerManagementService{

	@Override
	protected Class<Customer> getEntityClass() {
		return Customer.class;
	}
	
}
