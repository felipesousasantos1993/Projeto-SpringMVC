package br.com.projeto.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.projeto.entity.IEntity;

@Transactional
@Repository
public class CrudDAO implements ICrudDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(IEntity entity) {
		entityManager.persist(entity);
	}

	public void merge(IEntity entity) {
		entityManager.merge(entity);
	}

	public void remove(IEntity entity) {
		entityManager.remove(entity);
	}
}
