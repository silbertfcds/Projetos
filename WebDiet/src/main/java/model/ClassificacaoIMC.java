package model;

public enum ClassificacaoIMC {

	MAGREZA_GRAU_III("Magreza grau III"), 
	MAGREZA_GRAU_II("Magreza grau II"), 
	MAGREZA_GRAU_I("Magreza grau I"),
	ADEQUADO("Adequado"), 
	PRE_OBESO("Pré-obeso"), 
	OBESIDADE_GRAU_I("Obesidade grau I"),
	OBESIDADE_GRAU_III("Obesidade grau III"), 
	OBESIDADE_GRAU_II("Obesidade grau II");

	private String descricao;

	ClassificacaoIMC(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
