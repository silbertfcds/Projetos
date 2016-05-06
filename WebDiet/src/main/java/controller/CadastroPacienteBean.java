package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

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
	
	private AvaliacaoBioquimica removerAvaliacaoBioquimica;

	public CadastroPacienteBean() {
		limpar();
	}
	
	public void limpar(){
		novaAvaliacaoBioquimica = new AvaliacaoBioquimica();
		paciente = new Paciente();
		paciente.setEndereco(new Endereco());
		paciente.setHistorico(new Historico());
		paciente.getHistorico().setDoencas(new ArrayList<String>());
		paciente.getHistorico().setQueixas(new ArrayList<String>());
		paciente.setListaAvaliacaoAntropometrica(new ArrayList<AvaliacaoAntropometrica>());
		paciente.setListaAvaliacaoBioquimica(new ArrayList<AvaliacaoBioquimica>());
	}
	
	public void salvar() {
		paciente = pacienteService.salvar(paciente);
		//limpar();
		FacesUtil.addInfoMessage("Paciente adicionado com sucesso.");
	}
	
	public void adicionarAvaliacaoBioquimica(){
		novaAvaliacaoBioquimica = new AvaliacaoBioquimica();
	}
	
	public void salvarAvaliacaoBioquimica(){
		novaAvaliacaoBioquimica.setData(new Date());
		paciente.getListaAvaliacaoBioquimica().add(novaAvaliacaoBioquimica);
		novaAvaliacaoBioquimica.setPaciente(paciente);
		FacesUtil.addInfoMessage("Avaliação Bioquímica adicionada com sucesso.");
	}
	
	public void removerAvaliacaoBioquimica(){
		paciente.getListaAvaliacaoBioquimica().remove(removerAvaliacaoBioquimica);
		FacesUtil.addInfoMessage("Avaliação Bioquímica removida!");
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

	public AvaliacaoBioquimica getRemoverAvaliacaoBioquimica() {
		return removerAvaliacaoBioquimica;
	}

	public void setRemoverAvaliacaoBioquimica(
			AvaliacaoBioquimica removerAvaliacaoBioquimica) {
		this.removerAvaliacaoBioquimica = removerAvaliacaoBioquimica;
	}

}
