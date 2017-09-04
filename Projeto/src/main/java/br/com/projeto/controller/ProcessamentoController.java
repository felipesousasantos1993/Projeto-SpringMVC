package br.com.projeto.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.projeto.entity.Processamento;
import br.com.projeto.entity.Venda;
import br.com.projeto.enums.EnumStatus;
import br.com.projeto.service.IProcessamentoService;
import br.com.projeto.util.Util;

@Controller
public class ProcessamentoController {

	@Autowired
	private IProcessamentoService processamentoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessamentoController.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@RequestMapping(value = "/listarProcessados", method = RequestMethod.GET)
	public ResponseEntity<List<Processamento>> listaProcessados() {
		return new ResponseEntity<List<Processamento>>(processamentoService.findByStatus(EnumStatus.OK), HttpStatus.OK);
	}

	@RequestMapping("/gerarRegistroVendas")
	public ResponseEntity<String> gerarRegistroVendas() {
		try {
			List<Processamento> listaProcessamentosPendentes = processamentoService.findByStatus(EnumStatus.PENDENTE);
			int i = 1;
			File arquivoRegistro = null;
			for (Processamento processamento : listaProcessamentosPendentes) {
				if (i == 1 || i % 10 == 0) {
					arquivoRegistro = new File("C:\\PROCESSADOS\\" + UUID.randomUUID().toString() + ".txt");
					if (!arquivoRegistro.exists()) {
						arquivoRegistro.createNewFile();
					}
				}
				gerarRegistro(processamento, arquivoRegistro);
				Thread.sleep(60000L);
				i++;
			}

			return new ResponseEntity<String>("Registros gerados com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro ao gerar registros", HttpStatus.BAD_REQUEST);
		}
	}

	private void gerarRegistro(Processamento processamento, File arquivo) {
		processamento.setNomeArquivo(arquivo.getAbsolutePath());
		processamento.setStatus(EnumStatus.OK.getValue());
		Venda venda = Venda.gerarVendaRegistro(processamento.getVenda());
		Util.writeFile(arquivo, Util.convertObjectToJson(venda));
		processamentoService.merge(processamento);
		LOGGER.info("Processamento [" + processamento.getIdProcessamento() + "] as [" + sdf.format(new Date())
				+ "] registrado com sucesso!");
	}
}