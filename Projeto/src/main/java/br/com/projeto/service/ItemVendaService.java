package br.com.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.dao.IItemVendaDAO;
import br.com.projeto.entity.IEntity;
import br.com.projeto.entity.ItemVenda;

@Service
public class ItemVendaService implements IItemVendaService {

	@Autowired
	private IItemVendaDAO itemVendaDAO;

	@Override
	public List<ItemVenda> findAll() {
		return itemVendaDAO.findAll();
	}

	@Override
	public void persist(IEntity entity) {
		itemVendaDAO.persist(entity);
	}

	@Override
	public void merge(IEntity entity) {
		itemVendaDAO.merge(entity);
	}

	@Override
	public void remove(IEntity entity) {
		itemVendaDAO.remove(entity);
	}
}