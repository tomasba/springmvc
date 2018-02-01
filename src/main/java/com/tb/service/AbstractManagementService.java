package com.tb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tb.domain.Item;

public abstract class AbstractManagementService<T extends Item> implements ManagementService <T> {
	
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

	public abstract Map<Integer, T> getItems();
	
	public abstract Integer findNextId();
	
}
