package br.com.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.dao.IVendaDAO;
import br.com.projeto.entity.IEntity;
import br.com.projeto.entity.Venda;

@Service
public class VendaService implements IVendaService, ICrudService {
	@Autowired
	private IVendaDAO vendaDAO;

	@Override
	public List<Venda> findAll() {
		return vendaDAO.findAll();
	}

	@Override
	public List<Venda> findAllNaoProcessado() {
		return vendaDAO.findAllNaoProcessado();
	}

	@Override
	public void persist(IEntity entity) {
		vendaDAO.persist(entity);
	}

	@Override
	public void merge(IEntity entity) {
		vendaDAO.merge(entity);
	}

	@Override
	public void remove(IEntity entity) {
		vendaDAO.remove(entity);
	}

	@Override
	public Boolean existe(Venda venda) {
		return vendaDAO.existe(venda);
	}
}