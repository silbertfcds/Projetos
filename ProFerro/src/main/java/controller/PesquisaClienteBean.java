package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Cliente;
import repository.Clientes;
import repository.filter.ClienteFilter;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	private ClienteFilter filtro;
	private Cliente clienteSelecionado;
	private List<Cliente> clientesFiltrados;
	
	public PesquisaClienteBean() {
		filtro = new ClienteFilter();
		clientesFiltrados = new ArrayList<Cliente>();
	}

	public void pesquisar() {
		clientesFiltrados = clientes.filtrar(filtro);
		
		if(clientesFiltrados.size()==0){
			FacesUtil.addErrorMessage("A busca não retornou nenhum item");
		}
	}
	
	public void excluir(){
		clientes.remover(clienteSelecionado);
		clientesFiltrados.remove(clienteSelecionado);
		
		FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getNome()+ " excluído com sucesso.");
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

}
