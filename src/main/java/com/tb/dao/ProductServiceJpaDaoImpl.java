package com.tb.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tb.api.ProductManagementService;
import com.tb.domain.Product;

@Service("productManagementService")
@Profile("jpadao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoImpl<Product> implements ProductManagementService {

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}	
}
