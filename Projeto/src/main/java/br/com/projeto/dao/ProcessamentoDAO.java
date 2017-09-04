package br.com.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.entity.Processamento;
import br.com.projeto.enums.EnumStatus;

@Transactional
@Repository
public class ProcessamentoDAO extends CrudDAO implements IProcessamentoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Processamento> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT p FROM Processamento p ");
		Query query = entityManager.createQuery(hql.toString());
		return query.getResultList();
	}

	@Override
	public List<Processamento> findByStatus(EnumStatus status) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT p FROM Processamento p ");
		hql.append(" WHERE p.status =:status ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("status", status.getValue());
		return query.getResultList();
	}

}
