package com.tb.dao;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tb.api.BaseManagementService;
import com.tb.api.CustomerManagementService;
import com.tb.domain.Customer;

@Service("customerManagementService")
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoImpl<Customer> implements CustomerManagementService<Customer>{

//	@Override
//	public Customer saveOrUpdate(Customer item) {
//		getEntityManager().getTransaction().begin();
//		Customer customer = getEntityManager().merge(item);
//		getEntityManager().getTransaction().commit();
//		return customer;
//	}

//	@Override
//	public List<Customer> findAll() {		
//		return getEntityManager().createQuery("select t from Customer t", Customer.class).getResultList();
//	}
//
//	@Override
//	public Customer find(Integer id) {
//		return getEntityManager().find(Customer.class, id);
//	}
//
//	@Override
//	public void delete(Integer id) {
//		Customer customer = null;
//		if ((customer = getEntityManager().find(Customer.class, id)) != null) {
//			getEntityManager().getTransaction().begin();
//			getEntityManager().remove(customer);
//			getEntityManager().getTransaction().commit();
//		}
//	}

	@Override
	protected Class<Customer> getEntityClass() {
		return Customer.class;
	}
	
}
