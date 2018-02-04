package com.tb.api;

import java.util.List;

import com.tb.domain.DomainItem;

public interface BaseManagementService<T extends DomainItem> {
	
	public T saveOrUpdate(T item);

	public List<T> findAll();

	public T find(Integer id);

	public void delete(Integer id);
	
}