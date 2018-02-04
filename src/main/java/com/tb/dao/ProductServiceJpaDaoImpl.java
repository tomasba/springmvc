package com.tb.dao;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tb.api.ProductManagementService;
import com.tb.domain.Customer;
import com.tb.domain.Product;

@Service("productManagementService")
@Profile("jpadao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoImpl<Product> implements ProductManagementService<Product>{

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}	
}
