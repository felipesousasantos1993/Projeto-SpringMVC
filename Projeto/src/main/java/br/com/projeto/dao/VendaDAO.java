package br.com.projeto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.entity.Venda;
import br.com.projeto.enums.EnumStatus;

@Transactional
@Repository
public class VendaDAO extends CrudDAO implements IVendaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Venda> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT v FROM Venda v ");
		hql.append(" LEFT JOIN FETCH v.itens ");
		Query query = entityManager.createQuery(hql.toString());
		return query.getResultList();
	}

	@Override
	public List<Venda> findAllNaoProcessado() {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT distinct v FROM Venda v ");
		hql.append(" LEFT JOIN FETCH v.itens ");
		hql.append(" WHERE v.status =:status ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("status", EnumStatus.NAO_PROCESSADO.getValue());
		return query.getResultList();
	}

	@Override
	public Boolean existe(Venda venda) {
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT v.idVenda FROM Venda v ");
		hql.append(" WHERE v.idVenda=:idVenda ");
		try {
			Query query = entityManager.createQuery(hql.toString());
			query.setParameter("idVenda", venda.getIdVenda());
			query.getSingleResult();
			return Boolean.TRUE;
		} catch (NoResultException e) {
			return Boolean.FALSE;
		}
	}

}
