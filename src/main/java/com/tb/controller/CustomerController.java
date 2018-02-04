package com.tb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tb.api.CustomerManagementService;
import com.tb.domain.Customer;

@Controller
public class CustomerController {
	
	private CustomerManagementService customerManagementService;

	@RequestMapping(method=RequestMethod.GET, path="/customers/new")
	public String createCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customerform";
	}	
	
	@RequestMapping(path="/customers/update/{id}")
	public String editProduct(@PathVariable Integer id, Model model) {		
		model.addAttribute("customer", customerManagementService.find(id));
		return "customerform";
	}	
	
	@RequestMapping(method=RequestMethod.POST, path="/customers")
	public String saveOrUpdateCustomer(Customer customer) {
		customerManagementService.saveOrUpdate(customer);
		return "redirect:/customers/";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/customers")
	public String findAllCustomers(Model model) {
		model.addAttribute("customers", customerManagementService.findAll());
		return "customers";
	}

	@RequestMapping(path="/customers/{id}")
	public String findCustomer(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerManagementService.find(id));
		return "customer";
	}
	
	@RequestMapping(path="/customers/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id) {
		customerManagementService.delete(id);
		return "redirect:/customers/";
	}

	@Autowired
	//@Qualifier("customerManagementService")
	public void setCustomerManagementService(CustomerManagementService customerManagementService) {
		this.customerManagementService = customerManagementService;
	}

}
