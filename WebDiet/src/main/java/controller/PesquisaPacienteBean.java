package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Paciente;
import repository.Pacientes;
import repository.filter.PacienteFilter;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaPacienteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Pacientes pacientes;
	
	private Paciente pacienteSelecionado;
	private PacienteFilter filtro;
	
	private List<Paciente> pacientesFiltrados;

	
	public PesquisaPacienteBean() {
		filtro = new PacienteFilter();
		pacientesFiltrados = new ArrayList<Paciente>();
	}
	
	public void pesquisar(){
		pacientesFiltrados = pacientes.filtrar(filtro);
		
		if(pacientesFiltrados.size()==0){
			FacesUtil.addInfoMessage("A busca não retornou nenhum item");
		}
	}
	
	public void excluir(){
		pacientes.remover(pacienteSelecionado);
		pacientesFiltrados.remove(pacienteSelecionado);
		
		FacesUtil.addInfoMessage("Paciente " + pacienteSelecionado.getDadosPessoais().getNome()+ " excluído com sucesso.");
	}
	
	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public PacienteFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PacienteFilter filtro) {
		this.filtro = filtro;
	}

	public List<Paciente> getPacientesFiltrados() {
		return pacientesFiltrados;
	}
	
}
