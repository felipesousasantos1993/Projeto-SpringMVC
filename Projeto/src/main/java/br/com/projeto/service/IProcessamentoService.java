package br.com.projeto.service;

import java.util.List;

import br.com.projeto.entity.Processamento;
import br.com.projeto.enums.EnumStatus;

public interface IProcessamentoService extends ICrudService {

	public List<Processamento> findAll();

	List<Processamento> findByStatus(EnumStatus status);

}
