package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.entity.Venda;

public interface IVendaDAO extends ICrudDAO {

	Boolean existe(Venda venda);

	List<Venda> findAll();

	List<Venda> findAllNaoProcessado();
}
