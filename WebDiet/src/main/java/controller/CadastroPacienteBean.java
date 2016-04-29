package controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	private Endereco endereco;
	private Historico historico;

	public CadastroPacienteBean() {
		limpar();
	}
	
	public void limpar(){
		paciente = new Paciente();
		endereco = new Endereco();
		historico = new Historico();
	}
	
	public void salvar() {
		if(paciente.isNovo()){
			paciente.setEndereco(endereco);
			paciente.setHistorico(historico);
		}
		converterArray();
		paciente = pacienteService.salvar(paciente);
		//limpar();
		FacesUtil.addInfoMessage("Paciente adicionado com sucesso.");
	}
	
/*	public void adicionarTelefone(){
		if(!novoTelefone.getNumero().equals("") && novoTelefone.getTipoTelefone()!=null){
			paciente.getTelefones().add(novoTelefone);
			novoTelefone.setPaciente(paciente);
			novoTelefone = new Telefone();
			FacesUtil.addInfoMessage("Telefone adicionado com sucesso");
		}else{
			FacesUtil.addErrorMessage("Preencha todos os campos");
		}
		
	}
	
	public void adicionarEndereco(){
		if(!novoEndereco.getLogradouro().equals("")){
			paciente.getEnderecos().add(novoEndereco);
			novoEndereco.setPaciente(paciente);
			novoEndereco = new Endereco();
			FacesUtil.addInfoMessage("Endereço adicionado com sucesso");
		}else{
			FacesUtil.addErrorMessage("Campo logradouro vazio");
		}
	}

	public void excluirTelefone(){
		this.paciente.getTelefones().remove(removeTelefone);
		FacesUtil.addInfoMessage("Telefone removido!");
	}
	
	public void excluirEndereco(){
		this.paciente.getEnderecos().remove(removeEndereco);
		FacesUtil.addInfoMessage("Endereço removido!");
	}*/
	
	public Paciente getPaciente() {
		if(paciente == null){
			paciente = new Paciente();
		}
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public void converterArray(){
		List<String> doencas = Arrays.asList(paciente.getHistorico().getArrayHistoricoFamiliar());
		paciente.getHistorico().getDoencas().addAll(doencas);
		List<String> queixas = Arrays.asList(paciente.getHistorico().getArrayQueixas());
		paciente.getHistorico().getQueixas().addAll(queixas);
	}
  
}
