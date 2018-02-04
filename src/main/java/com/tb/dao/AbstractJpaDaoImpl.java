package com.tb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import com.tb.api.BaseManagementService;
import com.tb.domain.DomainItem;

public abstract class AbstractJpaDaoImpl <T extends DomainItem> implements BaseManagementService<T> {	
	
	private EntityManagerFactory emf;
	
	protected abstract Class<T> getEntityClass();
	
	@Override
	public T saveOrUpdate(T item) {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		T savedItem = em.merge(item);
		transaction.commit();
		return savedItem;
	}
	
	@Override
	public List<T> findAll() {		
		return getEntityManager().createQuery("select t from " +getEntityClass().getSimpleName()+ " t", getEntityClass()).getResultList();
	}

	@Override
	public T find(Integer id) {
		return getEntityManager().find(getEntityClass(), id);
	}

	@Override
	public void delete(Integer id) {
		T item = null;
		if ((item = getEntityManager().find(getEntityClass(), id)) != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(item);
			getEntityManager().getTransaction().commit();
		}
	}	
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}	

}
