package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Dieta;
import model.Paciente;
import repository.Dietas;
import repository.Pacientes;
import repository.filter.DietaFilter;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaDietaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pacientes pacientes;
	@Inject
	private Dietas dietas;

	private Dieta dietaSelecionado;
	private DietaFilter filtro;
	private List<Dieta> dietasFiltradas;

	public PesquisaDietaBean(){
		dietasFiltradas = new ArrayList<Dieta>();
		filtro = new DietaFilter();
	}
	
	public void pesquisar(){
		dietasFiltradas = dietas.filtrados(filtro);
		if(dietasFiltradas.size()==0){
			FacesUtil.addInfoMessage("A busca não retornou nenhum item");
		}
	}
	
	public void excluir(){
		dietas.remover(dietaSelecionado);
		dietasFiltradas.remove(dietaSelecionado);
		FacesUtil.addInfoMessage("Dieta excluída com sucesso.");
	}
	
	public Dieta getDietaSelecionado() {
		return dietaSelecionado;
	}

	public void setDietaSelecionado(Dieta dietaSelecionado) {
		this.dietaSelecionado = dietaSelecionado;
	}

	public List<Dieta> getDietasFiltradas() {
		return dietasFiltradas;
	}

	public void setDietasFiltradas(List<Dieta> dietasFiltradas) {
		this.dietasFiltradas = dietasFiltradas;
	}

	public List<Paciente> completarPaciente(String descricao){
		return pacientes.porNome(descricao);
	}

	public DietaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(DietaFilter filtro) {
		this.filtro = filtro;
	}
	
}
