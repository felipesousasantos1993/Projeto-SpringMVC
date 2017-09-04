package br.com.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.entity.ItemVenda;

@Transactional
@Repository
public class ItemVendaDAO extends CrudDAO implements IItemVendaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ItemVenda> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT iv FROM ItemVenda iv ");
		Query query = entityManager.createQuery(hql.toString());
		return query.getResultList();
	}

}
