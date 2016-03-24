package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.Dao;
import pl.spring.demo.to.IdAware;


public abstract class AbstractDaoImpl<T extends IdAware> implements Dao<T> {
	
	public abstract Set<T> getEntities();
	
	public List<T> findAll() {
		return new ArrayList<>(getEntities());
	}
	
	@NullableId
	public T save(T entity) {
		getEntities().add(entity);
		return entity;
	}
	
	public abstract Sequence getSequence();
    
    public abstract void setSequence(Sequence sequence);
}
