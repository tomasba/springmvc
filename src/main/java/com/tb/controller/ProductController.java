package com.tb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tb.domain.Product;
import com.tb.service.ProductService;

@Controller
public class ProductController {
	
	private ProductService productService;	

	@RequestMapping(method=RequestMethod.GET, path="/products")
	public String findAllProducts(Model model) {
		model.addAttribute("products", productService.findAllProducts());
		return "products";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/products/{id}")
	public String findProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.findProduc(id));
		return "product";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/products/new")
	public String createProduct(Model model) {
		model.addAttribute("product", new Product());
		return "productform";
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/products")
	public String saveOrUpdateProduct(Product product) {
		Product saved = productService.saveOrUpdateProduct(product);
		return "redirect:/products/" + saved.getId();
	}	
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}	
}
