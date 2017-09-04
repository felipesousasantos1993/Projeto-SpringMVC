package br.com.projeto.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.projeto.enums.EnumStatus;

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable, IEntity {

	private static final long serialVersionUID = 8797348050088166843L;

	@Id
	@Column(name = "id_venda", nullable = false, length = 11)
	@JsonProperty("id_venda")
	private Long idVenda;

	@Column(name = "data", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column(name = "pdv")
	private Integer pdv;

	@Column(name = "loja")
	private Integer loja;

	@Column(name = "status")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venda", orphanRemoval = true, targetEntity = ItemVenda.class)
	@JsonIgnore
	private List<ItemVenda> itens;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "venda")
	@JsonIgnore
	private Processamento processamento;

	public Venda() {
		super();
		itens = new ArrayList<ItemVenda>();
	}

	public static Venda gerarVendaRegistro(Venda venda) {
		Venda retorno = new Venda();
		retorno.setIdVenda(venda.getIdVenda());
		retorno.setPdv(venda.getPdv());
		retorno.setData(venda.getData());
		retorno.setLoja(venda.getLoja());
		retorno.setStatus(venda.getStatus());
		retorno.setProcessamento(null);
		if (null != venda.getItens() && venda.getItens().size() > 0) {
			for (ItemVenda itemVenda : venda.getItens()) {
				ItemVenda newItem = new ItemVenda();
				newItem.setIdItemVenda(itemVenda.getIdItemVenda());
				newItem.setDesconto(itemVenda.getDesconto());
				newItem.setPrecoUnitario(itemVenda.getPrecoUnitario());
				retorno.getItens().add(newItem);
			}
		}
		return retorno;
	}

	public Boolean isNaoProcessado() {
		return StringUtils.isEmpty(status) ? Boolean.FALSE : status.equals(EnumStatus.NAO_PROCESSADO.getValue());
	}

	public Boolean isOK() {
		return StringUtils.isEmpty(status) ? Boolean.FALSE : status.equals(EnumStatus.OK.getValue());
	}

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getPdv() {
		return pdv;
	}

	public void setPdv(Integer pdv) {
		this.pdv = pdv;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", data=" + data + ", pdv=" + pdv + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVenda == null) ? 0 : idVenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (idVenda == null) {
			if (other.idVenda != null)
				return false;
		} else if (!idVenda.equals(other.idVenda))
			return false;
		return true;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public Processamento getProcessamento() {
		return processamento;
	}

	public void setProcessamento(Processamento processamento) {
		this.processamento = processamento;
	}

	public Integer getLoja() {
		return loja;
	}

	public void setLoja(Integer loja) {
		this.loja = loja;
	}

}
