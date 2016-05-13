package repository.filter;

import java.io.Serializable;
import java.util.Date;

public class DietaFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomePaciente;
	
	private Date cadastroDe;
	private Date cadastroAte;

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public Date getCadastroDe() {
		return cadastroDe;
	}

	public void setCadastroDe(Date cadastroDe) {
		this.cadastroDe = cadastroDe;
	}

	public Date getCadastroAte() {
		return cadastroAte;
	}

	public void setCadastroAte(Date cadastroAte) {
		this.cadastroAte = cadastroAte;
	}

	
}
