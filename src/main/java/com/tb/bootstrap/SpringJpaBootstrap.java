package com.tb.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tb.api.CustomerManagementService;
import com.tb.api.ProductManagementService;
import com.tb.api.UserManagementService;
import com.tb.domain.Address;
import com.tb.domain.Customer;
import com.tb.domain.Product;
import com.tb.domain.User;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private ProductManagementService productManagementService; 
	private CustomerManagementService customerManagementService;
	private UserManagementService userManagementService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		createProducts();
		createCustomers();
	}

	private void createProducts() {
		Product p1 = new Product();
		p1.setDescription("product 1");
		p1.setImageUrl("www.url.com");
		p1.setPrice(new BigDecimal(123));
		
		Product p2 = new Product();
		p2.setDescription("product 2");
		p2.setImageUrl("www.url2.com");
		p2.setPrice(new BigDecimal(222));				
		
		Product p3 = new Product();
		p3.setDescription("product 3");
		p3.setImageUrl("www.url3.com");
		p3.setPrice(new BigDecimal(333));
		
		productManagementService.saveOrUpdate(p1);
		productManagementService.saveOrUpdate(p2);
		productManagementService.saveOrUpdate(p3);
	}
	
	private void createCustomers() {
		Customer c1 = new Customer();
		Address addrC1 = new Address();
		addrC1.setAddressLineOne("addr line 1 c1");
		addrC1.setAddressLineTwo("addr line 2 c1");
		addrC1.setState("cusct1 state");
		addrC1.setZipCode("cust1 zip");
		addrC1.setCity("addr 1 city");		
		c1.setShippingAddress(addrC1);
		c1.setBillingAddress(addrC1);
		c1.setEmail("adr1email");
		c1.setFirstName("Customer1 Name");
		c1.setLastName("Customer 1 last name");
		c1.setPhoneNumber("370111444555");
		User u1 = new User();
		u1.setEnabled(Boolean.TRUE);
		u1.setUsername("customer1 user1");
		u1.setPassword("customer1 user1");
		u1 = userManagementService.saveOrUpdate(u1);
		c1.setUser(u1);
		
		Customer c2 = new Customer();
		Address addrC2 = new Address();		
		addrC2.setAddressLineOne("addr line 1 c2");
		addrC2.setAddressLineTwo("addr line 2 c2");
		addrC2.setCity("addr 2 city");
		addrC2.setState("cusct2 state");
		addrC2.setZipCode("cust2 zip");		
		c2.setShippingAddress(addrC2);
		c2.setBillingAddress(addrC2);
		c2.setEmail("adr2email");
		c2.setFirstName("Customer2 Name");
		c2.setLastName("Customer 2 last name");
		c2.setPhoneNumber("370111422222");
		User u2 = new User();
		u2.setEnabled(Boolean.TRUE);
		u2.setUsername("customer2 user2");
		u2.setPassword("customer2 user2");
		u2 = userManagementService.saveOrUpdate(u2);
		c2.setUser(u2);		
		
		customerManagementService.saveOrUpdate(c1);
		customerManagementService.saveOrUpdate(c2);
	}
	
	@Autowired
	public void setProductManagementService(ProductManagementService productManagementService) {
		this.productManagementService = productManagementService;
	}

	@Autowired
	public void setCustomerManagementService(CustomerManagementService customerManagementService) {
		this.customerManagementService = customerManagementService;
	}

	@Autowired
	public void setUserManagementService(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

}
