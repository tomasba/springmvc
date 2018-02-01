package com.tb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tb.domain.Customer;
import com.tb.service.ManagementService;

@Controller
public class CustomerController {

	private ManagementService<Customer> customerService;

	@RequestMapping(method=RequestMethod.GET, path="/customers/new")
	public String createCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customerform";
	}	
	
	@RequestMapping(path="/customers/update/{id}")
	public String editProduct(@PathVariable Integer id, Model model) {		
		model.addAttribute("customer", customerService.find(id));
		return "customerform";
	}	
	
	@RequestMapping(method=RequestMethod.POST, path="/customers")
	public String saveOrUpdateCustomer(Customer customer) {
		customerService.saveOrUpdate(customer);
		return "redirect:/customers/";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/customers")
	public String findAllCustomers(Model model) {
		model.addAttribute("customers", customerService.findAll());
		return "customers";
	}

	@RequestMapping(path="/customers/{id}")
	public String findCustomer(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.find(id));
		return "customer";
	}
	
	@RequestMapping(path="/customers/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
		return "redirect:/customers/";
	}
	
	@Autowired
	public void setCustomerService(ManagementService<Customer> customerService) {
		this.customerService = customerService;
	}
	
}
