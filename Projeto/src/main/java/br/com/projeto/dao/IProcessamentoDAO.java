package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.entity.Processamento;
import br.com.projeto.enums.EnumStatus;

public interface IProcessamentoDAO extends ICrudDAO {
	List<Processamento> findAll();

	List<Processamento> findByStatus(EnumStatus status);
}
