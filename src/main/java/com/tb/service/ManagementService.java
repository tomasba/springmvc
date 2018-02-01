package com.tb.service;

import java.util.List;

public interface ManagementService <T>{

	T saveOrUpdate(T item);
	
	List<T> findAll();

	T find(Integer id);
	
	void delete(Integer id);	
	
}
