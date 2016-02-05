package model;

public enum TipoTelefone {

	CASA("Casa"), 
	CELULAR("Celular"),
	TRABALHO("Trabalho");
	
	private String descricao;
	
	TipoTelefone(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
