package model;

public enum Horario {

	CAFE("Cafe"), 
	LANCHEMANHA("Lanche Manhã"),
	ALMOCO("Almoco"),
	LANCHETARDE("Lanche Tarde"),
	JANTAR("Jantar"),
	CEIA("Ceia");
	
	private String descricao;
	
	Horario(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
