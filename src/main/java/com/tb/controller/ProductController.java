package com.tb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tb.domain.Product;
import com.tb.service.ManagementService;

@Controller
@RequestMapping(path="/products")
public class ProductController {
	
	private ManagementService<Product> productService;	

	@RequestMapping(method=RequestMethod.GET)
	public String findAllProducts(Model model) {
		model.addAttribute("products", productService.findAll());
		return "products";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{id}")
	public String findProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.find(id));
		return "product";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/new")
	public String createProduct(Model model) {
		model.addAttribute("product", new Product());
		return "productform";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveOrUpdateProduct(Product product) {
		Product saved = productService.saveOrUpdate(product);
		return "redirect:/products/" + saved.getId();
	}	
	
	@RequestMapping(path="/update/{id}")
	public String editProduct(@PathVariable Integer id, Model model) {		
		model.addAttribute("product", productService.find(id));
		return "productform";
	}
	
	@RequestMapping(path="/delete/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		productService.delete(id);
		return "redirect:/products";
	}

	@Autowired
	public void setProductService(ManagementService<Product> productService) {
		this.productService = productService;
	}
	

}
