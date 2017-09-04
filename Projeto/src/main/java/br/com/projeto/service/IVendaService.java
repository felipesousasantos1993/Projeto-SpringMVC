package br.com.projeto.service;

import java.util.List;

import br.com.projeto.entity.Venda;

public interface IVendaService extends ICrudService {

	Boolean existe(Venda venda);
	
	List<Venda> findAll();

	List<Venda> findAllNaoProcessado();

}
