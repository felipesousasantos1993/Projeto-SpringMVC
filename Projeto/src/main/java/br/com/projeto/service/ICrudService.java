package br.com.projeto.service;

import br.com.projeto.entity.IEntity;

public interface ICrudService {

	public void persist(IEntity entity);

	public void merge(IEntity entity);

	public void remove(IEntity entity);

}
