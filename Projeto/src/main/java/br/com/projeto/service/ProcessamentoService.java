package br.com.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.dao.IProcessamentoDAO;
import br.com.projeto.entity.IEntity;
import br.com.projeto.entity.Processamento;
import br.com.projeto.enums.EnumStatus;

@Service
public class ProcessamentoService implements IProcessamentoService {

	@Autowired
	private IProcessamentoDAO processamentoDAO;

	@Override
	public List<Processamento> findAll() {
		return processamentoDAO.findAll();
	}

	@Override
	public List<Processamento> findByStatus(EnumStatus status) {
		return processamentoDAO.findByStatus(status);
	}

	@Override
	public void persist(IEntity entity) {
		processamentoDAO.persist(entity);
	}

	@Override
	public void merge(IEntity entity) {
		processamentoDAO.merge(entity);
	}

	@Override
	public void remove(IEntity entity) {
		processamentoDAO.remove(entity);
	}
}