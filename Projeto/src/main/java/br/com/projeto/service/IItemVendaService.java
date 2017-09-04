package br.com.projeto.service;

import java.util.List;

import br.com.projeto.entity.ItemVenda;

public interface IItemVendaService extends ICrudService {

	public List<ItemVenda> findAll();

}
