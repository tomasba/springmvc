package com.tb.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tb.api.ProductManagementService;
import com.tb.config.TestJpaEnvironmentConfig;
import com.tb.domain.Product;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class) // set-up spring context//
@ContextConfiguration(classes={TestJpaEnvironmentConfig.class})
@ActiveProfiles({"jpadao"})
public class ProductServiceJpaDaoImplTest {

	private ProductManagementService productManagementService;
	
	@Test
	public void shouldRetrieveAllProductsBootstrapped() {
		List<Product> result = productManagementService.findAll();
		
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(3));
	}
	
	@Test
	public void shouldRemoveProduct() {
		List<Product> result = productManagementService.findAll();
		Integer id1 = result.get(0).getId();
		productManagementService.delete(id1);
		result = productManagementService.findAll();
		
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
	}	
	
	@Autowired
	public void setProductManagementService(ProductManagementService productManagementService) {
		this.productManagementService = productManagementService;
	} 
	
}
