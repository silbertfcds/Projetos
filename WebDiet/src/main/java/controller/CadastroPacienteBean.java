package controller;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Endereco;
import model.Paciente;
import model.Telefone;
import model.TipoTelefone;
import service.PacienteService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteService pacienteService;

	private Telefone novoTelefone;
	private Telefone removeTelefone;
	
	private Endereco novoEndereco;
	private Endereco removeEndereco;
	
	@Produces
	@PacienteEdicao
	private Paciente paciente;

	public CadastroPacienteBean() {
		limpar();
	}
	
	public void limpar(){
		paciente = new Paciente();
		novoTelefone = new Telefone();
		novoEndereco = new Endereco();
		
	}
	
	public void salvar() {
		paciente = pacienteService.salvar(paciente);
		limpar();
		FacesUtil.addInfoMessage("Paciente adicionado com sucesso.");
	}
	
	public void adicionarTelefone(){
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

	public TipoTelefone[] getTiposTelefones(){
		return TipoTelefone.values();
	}
	
	public Telefone getNovoTelefone() {
		return novoTelefone;
	}

	public void setNovoTelefone(Telefone novoTelefone) {
		this.novoTelefone = novoTelefone;
	}

	public Telefone getRemoveTelefone() {
		return removeTelefone;
	}

	public void setRemoveTelefone(Telefone removeTelefone) {
		this.removeTelefone = removeTelefone;
	}

	public Endereco getNovoEndereco() {
		return novoEndereco;
	}

	public void setNovoEndereco(Endereco novoEndereco) {
		this.novoEndereco = novoEndereco;
	}

	public Endereco getRemoveEndereco() {
		return removeEndereco;
	}

	public void setRemoveEndereco(Endereco removeEndereco) {
		this.removeEndereco = removeEndereco;
	}
	
}
