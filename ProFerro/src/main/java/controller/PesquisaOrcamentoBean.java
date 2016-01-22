package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Orcamento;
import repository.Orcamentos;
import repository.filter.OrcamentoFilter;
import util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaOrcamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Orcamentos orcamentos;
	
	private List<Orcamento> orcamentosFiltrados;
	
	private OrcamentoFilter filtro;

	
	public PesquisaOrcamentoBean(){
		filtro = new OrcamentoFilter();
		orcamentosFiltrados = new ArrayList<>();
	}
	
	public void pesquisar(){
		orcamentosFiltrados = orcamentos.filtrados(filtro);
		
		if(orcamentosFiltrados.size()==0){
			FacesUtil.addErrorMessage("A busca não retornou nenhum item");
		}
	}
	
	public List<Orcamento> getOrcamentosFiltrados() {
		return orcamentosFiltrados;
	}

	public void setOrcamentosFiltrados(List<Orcamento> orcamentosFiltrados) {
		this.orcamentosFiltrados = orcamentosFiltrados;
	}

	public OrcamentoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(OrcamentoFilter filtro) {
		this.filtro = filtro;
	}
	
	
	

}
