package com.tb.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tb.api.BaseManagementService;
import com.tb.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by jt on 11/16/15.
 */
public class ProductControllerTest {

	@Mock
	private BaseManagementService<Product> productService;

	@InjectMocks
	private ProductController productController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this); // initilizes controller and mocks

		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void souldFindAllProducts() throws Exception {
		// given
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		when(productService.findAll()).thenReturn((List<Product>) products);

		// when
		ResultActions result = mockMvc.perform(get("/products/"));

		// then
		result.andExpect(status().isOk()).andExpect(view().name("products"))
				.andExpect(model().attribute("products", hasSize(2)));
	}

	@Test
	public void shouldFindProductById() throws Exception {
		// given
		Integer id = 1;
		when(productService.find(id)).thenReturn(new Product());

		// when
		ResultActions result = mockMvc.perform(get("/products/1"));

		// then
		result.andExpect(status().isOk()).andExpect(view().name("product"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));
	}

	@Test
	public void shouldOpenUpdateForm() throws Exception {
		// given
		Integer id = 1;
		when(productService.find(id)).thenReturn(new Product());
		// when
		ResultActions result = mockMvc.perform(get("/products/update/1"));
		// then
		result.andExpect(status().isOk()).andExpect(view().name("productform"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));
	}

	@Test
	public void shouldOpenNewProductForm() throws Exception {
		verifyZeroInteractions(productService);
		// when
		ResultActions result = mockMvc.perform(get("/products/new"));
		// then
		result.andExpect(status().isOk()).andExpect(view().name("productform"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));
	}

	@Test
	public void shouldSaveOrUpdate() throws Exception {
		Integer id = 1;
		String description = "Test Description";
		BigDecimal price = new BigDecimal("12.00");
		String imageUrl = "example.com";

		Product returnProduct = new Product();
		returnProduct.setId(id);
		returnProduct.setDescription(description);
		returnProduct.setPrice(price);
		returnProduct.setImageUrl(imageUrl);

		when(productService.saveOrUpdate(Matchers.<Product>any())).thenReturn(returnProduct);

		mockMvc.perform(post("/products").param("id", "1").param("description", description).param("price", "12.00")
				.param("imageUrl", "example.com")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/products/1"))
				.andExpect(model().attribute("product", instanceOf(Product.class)))
				.andExpect(model().attribute("product", hasProperty("id", is(id))))
				.andExpect(model().attribute("product", hasProperty("description", is(description))))
				.andExpect(model().attribute("product", hasProperty("price", is(price))))
				.andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))));

		// verify properties of bound object
		ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
		verify(productService).saveOrUpdate(boundProduct.capture());

		assertEquals(id, boundProduct.getValue().getId());
		assertEquals(description, boundProduct.getValue().getDescription());
		assertEquals(price, boundProduct.getValue().getPrice());
		assertEquals(imageUrl, boundProduct.getValue().getImageUrl());
	}

	@Test
	public void testDelete() throws Exception {
		Integer id = 1;

		mockMvc.perform(get("/products/delete/1")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/products"));

		verify(productService, times(1)).delete(id);
	}
}
