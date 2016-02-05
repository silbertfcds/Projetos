package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Alimento;
import service.AlimentoService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAlimentoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private AlimentoService service;
	
	private Alimento alimento;
	
	public CadastroAlimentoBean(){
		limpar();
	}

	public void salvar(){
		alimento = service.salvar(alimento);
		limpar();
		FacesUtil.addInfoMessage("Alimento adicionado com sucesso.");
	}
	
	public Alimento getAlimento() {
		if(alimento == null){
			alimento = new Alimento();
		}
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	
	public void limpar(){
		alimento = new Alimento();
	}
	
}
