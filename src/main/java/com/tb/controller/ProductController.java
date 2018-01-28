package com.tb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tb.service.ProductService;

@Controller
public class ProductController {
	
	private ProductService productService;	

	@RequestMapping("/products")
	public String findAllProducts(Model model) {
		model.addAttribute("products", productService.findAllProducts());
		return "products";
	}
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}	
}
