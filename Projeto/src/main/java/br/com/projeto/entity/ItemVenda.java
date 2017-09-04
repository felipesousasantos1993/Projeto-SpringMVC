package br.com.projeto.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_item_venda")
public class ItemVenda implements Serializable, IEntity {

	private static final long serialVersionUID = 6333775246508435299L;

	@Id
	@Column(name = "id_item_venda")
	@JsonProperty("id_item_venda")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItemVenda;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, targetEntity = Venda.class)
	@JoinColumn(name = "id_venda")
	@JsonProperty("id_venda")
	private Venda venda;

	@Column(name = "preco_unitario")
	@JsonProperty("preco_unitario")
	private Double precoUnitario;

	@Column(name = "desconto")
	private Double desconto;

	@Column(name = "produto")
	private String produto;

	public Long getIdItemVenda() {
		return idItemVenda;
	}

	public void setIdItemVenda(Long idItemVenda) {
		this.idItemVenda = idItemVenda;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public String toString() {
		return "ItemVenda [idItemVenda=" + idItemVenda + ", venda=" + venda + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItemVenda == null) ? 0 : idItemVenda.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		ItemVenda other = (ItemVenda) obj;
		if (idItemVenda == null) {
			if (other.idItemVenda != null)
				return false;
		} else if (!idItemVenda.equals(other.idItemVenda))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

}
