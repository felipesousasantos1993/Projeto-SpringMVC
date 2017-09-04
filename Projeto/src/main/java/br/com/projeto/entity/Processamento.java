package br.com.projeto.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.projeto.enums.EnumStatus;

@Entity
@Table(name = "tb_processamento")
public class Processamento implements Serializable, IEntity {

	private static final long serialVersionUID = -1440800733184964076L;

	@Id
	@Column(name = "id_processamento")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(value = "id_processamento")
	private Long idProcessamento;

	@Column(name = "data", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column(name = "pdv")
	private Integer pdv;

	@Column(name = "status")
	private String status;

	@Column(name = "loja")
	private Integer loja;

	@Column(name = "nome_arquivo")
	@JsonProperty(value = "nome_arquivo")
	private String nomeArquivo;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, targetEntity = Venda.class)
	@JoinColumn(name = "id_venda", referencedColumnName = "id_venda")
	@JsonProperty(value = "nome_arquivo")
	@JsonIgnore
	private Venda venda;

	public Boolean isPendente() {
		return StringUtils.isEmpty(status) ? Boolean.FALSE : status.equals(EnumStatus.PENDENTE.getValue());
	}

	public Long getIdProcessamento() {
		return idProcessamento;
	}

	public void setIdProcessamento(Long idProcessamento) {
		this.idProcessamento = idProcessamento;
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

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public String toString() {
		return "Processamento [idProcessamento=" + idProcessamento + ", nomeArquivo=" + nomeArquivo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProcessamento == null) ? 0 : idProcessamento.hashCode());
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
		Processamento other = (Processamento) obj;
		if (idProcessamento == null) {
			if (other.idProcessamento != null)
				return false;
		} else if (!idProcessamento.equals(other.idProcessamento))
			return false;
		return true;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Integer getLoja() {
		return loja;
	}

	public void setLoja(Integer loja) {
		this.loja = loja;
	}

}
