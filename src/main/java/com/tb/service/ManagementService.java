package com.tb.service;

import java.util.List;

import com.tb.domain.Item;

public interface ManagementService <T extends Item> {

	T saveOrUpdate(T item);
	
	List<T> findAll();

	T find(Integer id);
	
	void delete(Integer id);	
	
}
