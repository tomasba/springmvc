package com.tb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tb.api.BaseManagementService;
import com.tb.domain.Customer;
import com.tb.domain.DomainItem;

public abstract class AbstractManagementService<T extends DomainItem> implements BaseManagementService <T> {	
	
	private Map<Integer, T> items = new HashMap<>();
	
	@Override
	public T saveOrUpdate(T item) {
		if (item != null && item.getId() == null) {
			item.setId(findNextId());	
		}						
		getItems().put(item.getId(), item);
		return item;
	}	
	
	@Override
	public List<T> findAll() {
		return new ArrayList<>(getItems().values());
	}	
	
	@Override
	public T find(Integer id) {
		return getItems().get(id);
	}
	
	@Override
	public void delete(Integer id) {
		getItems().remove(id);
	}

	public Map<Integer, T> getItems() {
		return items;
	}
	
	public Integer findNextId() {
		return items.size() + 1;		
	}
	
}
