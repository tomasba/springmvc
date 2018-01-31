package com.tb.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tb.domain.Customer;
import com.tb.service.CustomerService;

@Controller
public class CustomerController {

	private CustomerService customerService;

	//public Customer saveOrUpdateCustomer(Customer customer);
	
	@RequestMapping(path="/customers")
	public String findAllCustomers(Model model) {
		model.addAttribute("customers", customerService.findAllCustomers());
		return "customers";
	}

	@RequestMapping(path="/customers/{id}")
	public String findCustomer(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.findCustomer(id));
		return "customer";
	}
	
	//public void deleteCustomer(Integer id);	
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
}
