package br.com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.entity.ItemVenda;
import br.com.projeto.service.IItemVendaService;

@Controller
public class ItemVendaController {

	@Autowired
	private IItemVendaService iItemVendaService;

	@RequestMapping("/itemVendas")
	public ResponseEntity<List<ItemVenda>> findAll() {
		List<ItemVenda> lista = iItemVendaService.findAll();
		return new ResponseEntity<List<ItemVenda>>(lista, HttpStatus.OK);
	}
}