package br.com.projeto.dao;

import br.com.projeto.entity.IEntity;

public interface ICrudDAO {

	public void persist(IEntity entity);

	public void merge(IEntity entity);

	public void remove(IEntity entity);
}
