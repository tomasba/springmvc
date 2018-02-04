package com.tb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tb.api.ProductManagementService;
import com.tb.domain.Product;

@Controller
@RequestMapping(path="/products")
public class ProductController {
		
	private ProductManagementService<Product> productManagementService;	

	@RequestMapping(method=RequestMethod.GET)
	public String findAllProducts(Model model) {
		model.addAttribute("products", productManagementService.findAll());
		return "products";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{id}")
	public String findProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productManagementService.find(id));
		return "product";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/new")
	public String createProduct(Model model) {
		model.addAttribute("product", new Product());
		return "productform";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveOrUpdateProduct(Product product) {
		Product saved = productManagementService.saveOrUpdate(product);
		return "redirect:/products/" + saved.getId();
	}	
	
	@RequestMapping(path="/update/{id}")
	public String editProduct(@PathVariable Integer id, Model model) {		
		model.addAttribute("product", productManagementService.find(id));
		return "productform";
	}
	
	@RequestMapping(path="/delete/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		productManagementService.delete(id);
		return "redirect:/products";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" }) // autowire injection fails with generics applied
	@Autowired
	@Qualifier("productManagementService")
	public void setProductManagementService(ProductManagementService productManagementService) {
		this.productManagementService = productManagementService;
	}

}
