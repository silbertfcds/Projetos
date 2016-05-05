package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.AvaliacaoAntropometrica;
import model.AvaliacaoBioquimica;
import model.Endereco;
import model.Historico;
import model.Paciente;
import service.PacienteService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteService pacienteService;
	@Produces
	@PacienteEdicao
	private Paciente paciente;
	
	private AvaliacaoAntropometrica novaAvaliacaoAntropometrica;
	private AvaliacaoBioquimica novaAvaliacaoBioquimica;

	public CadastroPacienteBean() {
		limpar();
	}
	
	public void limpar(){
		paciente = new Paciente();
		paciente.setEndereco(new Endereco());
		paciente.setHistorico(new Historico());
		paciente.getHistorico().setDoencas(new ArrayList<String>());
		paciente.getHistorico().setQueixas(new ArrayList<String>());
	}
	
	public void salvar() {
		paciente = pacienteService.salvar(paciente);
		//limpar();
		FacesUtil.addInfoMessage("Paciente adicionado com sucesso.");
	}
	
	public AvaliacaoAntropometrica getNovaAvaliacaoAntropometrica() {
		return novaAvaliacaoAntropometrica;
	}

	public void setNovaAvaliacaoAntropometrica(
			AvaliacaoAntropometrica novaAvaliacaoAntropometrica) {
		this.novaAvaliacaoAntropometrica = novaAvaliacaoAntropometrica;
	}

	public AvaliacaoBioquimica getNovaAvaliacaoBioquimica() {
		return novaAvaliacaoBioquimica;
	}

	public void setNovaAvaliacaoBioquimica(
			AvaliacaoBioquimica novaAvaliacaoBioquimica) {
		this.novaAvaliacaoBioquimica = novaAvaliacaoBioquimica;
	}

	public Paciente getPaciente() {
		if(paciente == null){
			paciente = new Paciente();
		}
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
