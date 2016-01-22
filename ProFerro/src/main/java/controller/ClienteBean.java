package controller;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Cliente;
import model.Endereco;
import model.Telefone;
import model.TipoPessoa;
import model.TipoTelefone;
import service.ClienteService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteService clienteService;
	
	@Produces
	@ClienteEdicao
	private Cliente cliente;
	
	private Endereco novoEndereco;
	
	private Endereco removeEndereco;
	
	private Telefone novoTelefone;
	
	private Telefone removeTelefone;

	private boolean mostrarPessoa = false;
	
	public ClienteBean() {
		limpar();
	}

	public void salvar(){
		cliente = clienteService.salvar(cliente);
		limpar();
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
	}
	
	public void mostrarQuebraTipoPessoa(){
		if(cliente.getTipoPessoa().getDescricao().equals("Fisica")){
			mostrarPessoa = true;
		}else{
			mostrarPessoa = false;
		}
	}
	public void adicionarEndereco(){
		if(!novoEndereco.getLogradouro().equals("")){
			cliente.getEnderecos().add(novoEndereco);
			novoEndereco.setCliente(cliente);
			novoEndereco = new Endereco();
			FacesUtil.addInfoMessage("Endereço adicionado com sucesso");
		}else{
			FacesUtil.addErrorMessage("Campo logradouro vazio");
		}
	}
	
	public void adicionarTelefone(){
		if(!novoTelefone.getNumero().equals("") && novoTelefone.getTipoTelefone()!=null){
			cliente.getTelefones().add(novoTelefone);
			novoTelefone.setCliente(cliente);
			novoTelefone = new Telefone();
			FacesUtil.addInfoMessage("Telefone adicionado com sucesso");
		}else{
			FacesUtil.addErrorMessage("Preencha todos os campos");
		}
		
	}
	
	public void excluirEndereco(){
		this.cliente.getEnderecos().remove(removeEndereco);
		FacesUtil.addInfoMessage("Endereço removido!");
	}
	
	public void excluirTelefone(){
		this.cliente.getTelefones().remove(removeTelefone);
		FacesUtil.addInfoMessage("Telefone removido!");
	}
	
	public Cliente getCliente() {
		if(cliente == null){
			cliente = new Cliente();
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		if(cliente!=null){
			cliente.getEnderecos();
		}
	}

	public boolean isEditando() {
        boolean resultado = false;
        if (this.cliente != null) {
            resultado = this.cliente.getId() != null;
        }
        return resultado;
    }

	public TipoPessoa[] getTiposPessoas(){
		return TipoPessoa.values();
	}
	
	public TipoTelefone[] getTiposTelefones(){
		return TipoTelefone.values();
	}
	
	private void limpar() {
		cliente = new Cliente();
		novoEndereco = new Endereco();
		novoTelefone = new Telefone();
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

	public boolean isMostrarPessoa() {
		return mostrarPessoa;
	}

	public void setMostrarPessoa(boolean mostrarPessoa) {
		this.mostrarPessoa = mostrarPessoa;
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
