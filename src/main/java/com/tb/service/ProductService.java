package com.tb.service;

import java.util.List;

import com.tb.domain.Product;

public interface ProductService {
	
	Product saveOrUpdateProduct(Product product);
	
	List<Product> findAllProducts();

	Product findProduct(Integer id);
}
