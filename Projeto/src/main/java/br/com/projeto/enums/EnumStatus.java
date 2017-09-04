package br.com.projeto.enums;

public enum EnumStatus {

	OK(1, "OK"),
	PENDENTE(2, "PENDENTE"),
	NAO_PROCESSADO(3, "NÃO PROCESSADO");
	
	private Integer id;
	private String value;
	
	private EnumStatus(Integer id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public Integer getId() {
		return id;
	}
	public String getValue() {
		return value;
	}

}
