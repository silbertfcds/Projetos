package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Alimento;
import repository.Alimentos;
import repository.filter.AlimentoFilter;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAlimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Alimentos alimentos;

	private AlimentoFilter filtro;
	private Alimento alimentoSelecionado;
	private List<Alimento> alimentosFiltrados;

	public PesquisaAlimentoBean() {
		alimentosFiltrados = new ArrayList<Alimento>();
		filtro = new AlimentoFilter();
	}

	public void pesquisar() {
		alimentosFiltrados = alimentos.filtrar(filtro);
		if(alimentosFiltrados.size()==0){
			FacesUtil.addInfoMessage("A busca não retornou nenhum item");
		}
	}

	public void excluir(){
		alimentos.remover(alimentoSelecionado);
		alimentosFiltrados.remove(alimentoSelecionado);
		
		FacesUtil.addInfoMessage("Alimento " + alimentoSelecionado.getDescricao()+ " excluído com sucesso.");
	}
	
	public AlimentoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AlimentoFilter filtro) {
		this.filtro = filtro;
	}

	public Alimento getAlimentoSelecionado() {
		return alimentoSelecionado;
	}

	public void setAlimentoSelecionado(Alimento alimentoSelecionado) {
		this.alimentoSelecionado = alimentoSelecionado;
	}

	public List<Alimento> getAlimentosFiltrados() {
		return alimentosFiltrados;
	}

}
