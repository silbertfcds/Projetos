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
	
	private Endereco endereco;
	
	private Telefone telefone;

	
	public ClienteBean() {
		limpar();
	}

	public void salvar(){
		cliente = clienteService.salvar(cliente);
		limpar();
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
	}
	
	public void adicionarEndereco(){	
		if(endereco!=null){
			cliente.getEnderecos().add(endereco);
			endereco.setCliente(cliente);
			endereco = new Endereco();
		}else{
			endereco = new Endereco();
		}
	}
	
	public void adicionarTelefone(){
		if(telefone!=null){
			cliente.getTelefones().add(telefone);
			telefone.setCliente(cliente);
			telefone = new Telefone();
		}else{
			telefone = new Telefone();
		}
	}
	
	public void excluirEndereco(){
		if(endereco!=null){
			cliente.getEnderecos().remove(endereco);
			//endereco = new Endereco();
		}
		
	}
	
	public void excluirTelefone(){
		if(telefone!=null){
			cliente.getTelefones().remove(telefone);
		}
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		endereco = new Endereco();
		telefone = new Telefone();
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	
}
