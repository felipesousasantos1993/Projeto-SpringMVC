package br.com.projeto.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.projeto.entity.ItemVenda;
import br.com.projeto.entity.Processamento;
import br.com.projeto.entity.Venda;
import br.com.projeto.enums.EnumStatus;
import br.com.projeto.service.IProcessamentoService;
import br.com.projeto.service.IVendaService;

@Controller
public class VendaController implements Serializable {

	private static final long serialVersionUID = 6225058167901564383L;

	@Autowired
	private transient IVendaService vendaService;

	@Autowired
	private transient IProcessamentoService processamentoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(VendaController.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@RequestMapping("/processarVendas")
	public ResponseEntity<String> processVendas() {
		try {
			List<Venda> listaNaoProcessado = vendaService.findAllNaoProcessado();

			if (listaNaoProcessado.isEmpty()) {
				return new ResponseEntity<String>("Não existe vendas a processar!", HttpStatus.OK);
			}
			for (Venda venda : listaNaoProcessado) {
				processarVenda(venda);
				Thread.sleep(1000L);
			}

			return new ResponseEntity<String>("Vendas Processadas com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<String>("Ocorreu um erro ao processar as vendas!", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/receberVenda", method = RequestMethod.POST)
	public ResponseEntity<String> receberVenda(@RequestBody Venda venda) {
		try {
			for (ItemVenda itemVenda : venda.getItens()) {
				itemVenda.setVenda(venda);
			}
			processarVenda(venda);

			return new ResponseEntity<String>("Venda Recebida Com Sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<String>("Ocorreu um erro ao processar as vendas!", HttpStatus.BAD_REQUEST);
		}
	}

	private void processarVenda(Venda venda) {
		Processamento processamento = new Processamento();
		processamento.setData(venda.getData());
		processamento.setPdv(venda.getPdv());
		processamento.setStatus(EnumStatus.PENDENTE.getValue());
		processamento.setLoja(venda.getLoja());
		venda.setStatus(EnumStatus.OK.getValue());
		vendaService.merge(venda);
		processamento.setVenda(venda);
		processamentoService.persist(processamento);
		LOGGER.info("Venda [" + venda.getIdVenda() + "] as [" + sdf.format(new Date()) + "] processada com sucesso!");
	}

}