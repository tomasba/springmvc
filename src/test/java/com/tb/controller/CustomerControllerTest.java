package com.tb.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tb.domain.Customer;
import com.tb.service.ManagementService;

public class CustomerControllerTest {

	@Mock
	private ManagementService<Customer> customerService;

	@InjectMocks
	private CustomerController customerController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void shouldReceiveAllCustomers() throws Exception {
		// given
		List<Customer> customers = Lists.newArrayList();
		Customer c1 = new Customer();
		customers.add(c1);
		given(customerService.findAll()).willReturn(customers);

		// when
		ResultActions result = mockMvc.perform(get("/customers/"));

		// then
		result.andExpect(status().isOk()).andExpect(view().name("customers"))
				.andExpect(model().attributeExists("customers"))
				.andExpect(model().attribute("customers", IsCollectionWithSize.hasSize(1)));

		verify(customerService, times(1)).findAll();
	}

	@Test
	public void shouldFindCustomerById() throws Exception {
		// given
		Customer customer = new Customer();
		given(customerService.find(1)).willReturn(customer);

		// when
		ResultActions result = mockMvc.perform(get("/customers/1"));

		// then
		result.andExpect(status().isOk()).andExpect(view().name("customer"))
				.andExpect(model().attributeExists("customer"))
				.andExpect(model().attribute("customer", instanceOf(Customer.class)))
				.andExpect(model().attribute("customer", customer));
		verify(customerService).find(1);
	}

	@Test
	public void shouldSaveOrUpdate() throws Exception {
		Integer id = 1;
		String addressLineOne = "addressLineOne";
		String addressLineTwo = "addressLineTwo";
		String city = "city";
		String email = "email";
		String firstName = "firstName";
		String lastName = "lastName";
		String phoneNumber = "phoneNumber";
		String state = "state";
		String zipCode = "zipCode";

		Customer customer = new Customer();
		customer.setId(id);
		customer.setAddressLineOne(addressLineOne);
		customer.setAddressLineTwo(addressLineTwo);
		customer.setCity(city);
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPhoneNumber(phoneNumber);
		customer.setState(state);
		customer.setZipCode(zipCode);

		when(customerService.saveOrUpdate(Matchers.<Customer>any())).thenReturn(customer);

		mockMvc.perform(post("/customers").param("id", "1").param("addressLineOne", addressLineOne)
				.param("addressLineTwo", addressLineTwo).param("city", city).param("email", email)
				.param("firstName", firstName).param("lastName", lastName).param("phoneNumber", phoneNumber)
				.param("state", state).param("zipCode", zipCode)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/customers/"))
				.andExpect(model().attribute("customer", instanceOf(Customer.class)))
				.andExpect(model().attribute("customer", hasProperty("id", equalTo(id))))
				.andExpect(model().attribute("customer", hasProperty("addressLineOne", equalTo(addressLineOne))))
				.andExpect(model().attribute("customer", hasProperty("addressLineTwo", equalTo(addressLineTwo))))
				.andExpect(model().attribute("customer", hasProperty("city", equalTo(city))))
				.andExpect(model().attribute("customer", hasProperty("email", equalTo(email))))
				.andExpect(model().attribute("customer", hasProperty("firstName", equalTo(firstName))))
				.andExpect(model().attribute("customer", hasProperty("lastName", equalTo(lastName))))
				.andExpect(model().attribute("customer", hasProperty("phoneNumber", equalTo(phoneNumber))))
				.andExpect(model().attribute("customer", hasProperty("state", equalTo(state))))
				.andExpect(model().attribute("customer", hasProperty("zipCode", equalTo(zipCode))));

		// verify properties of bound object
		ArgumentCaptor<Customer> boundProduct = ArgumentCaptor.forClass(Customer.class);
		verify(customerService).saveOrUpdate(boundProduct.capture());

		assertThat(id, equalTo(boundProduct.getValue().getId()));
		assertThat(addressLineOne, equalTo(boundProduct.getValue().getAddressLineOne()));
		assertThat(addressLineTwo, equalTo(boundProduct.getValue().getAddressLineTwo()));
		assertThat(city, equalTo(boundProduct.getValue().getCity()));
		assertThat(email, equalTo(boundProduct.getValue().getEmail()));
		assertThat(firstName, equalTo(boundProduct.getValue().getFirstName()));
		assertThat(lastName, equalTo(boundProduct.getValue().getLastName()));
		assertThat(phoneNumber, equalTo(boundProduct.getValue().getPhoneNumber()));
		assertThat(state, equalTo(boundProduct.getValue().getState()));
		assertThat(zipCode, equalTo(boundProduct.getValue().getZipCode()));
	}
}
