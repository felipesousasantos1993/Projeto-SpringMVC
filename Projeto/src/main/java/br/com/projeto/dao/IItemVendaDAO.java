package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.entity.ItemVenda;

public interface IItemVendaDAO extends ICrudDAO {

	List<ItemVenda> findAll();

}
