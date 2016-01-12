package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Cliente;
import model.ItemOrcamento;
import model.Orcamento;
import repository.Clientes;
import service.OrcamentoService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class OrcamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	@Inject
	private OrcamentoService service;
	@Produces
	@OrcamentoEdicao
	private Orcamento orcamento;
	
	private ItemOrcamento item;
	
	public OrcamentoBean(){
		limpar();
	}

	public void salvar(){
		service.salvar(orcamento);
		limpar();
		FacesUtil.addInfoMessage("Orçamento salvo com sucesso!");
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			if(this.orcamento==null){
				this.orcamento = new Orcamento();
			}
			recalcularOrcamento();
		}
	}
	
	public void limpar() {
		orcamento = new Orcamento();
		item = new ItemOrcamento();
	}
	
	public void adicionarItem(){
		if(item!=null){
			orcamento.getItens().add(item);
			item.setOrcamento(orcamento);
			orcamento.recalcularValorTotal();
			item = new ItemOrcamento();
		}else{
			item = new ItemOrcamento();
		}
	}
	
	public void excluirItem(){
		if(item!=null){
			orcamento.getItens().remove(item);
			orcamento.recalcularValorTotal();
		}
	}
	
	public void recalcularOrcamento() {
		if (this.orcamento != null) {
			this.orcamento.recalcularValorTotal();
		}
	}
	
	public List<Cliente> completarCliente(String nome) {
		return clientes.porNome(nome);
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public ItemOrcamento getItem() {
		return item;
	}

	public void setItem(ItemOrcamento item) {
		this.item = item;
	}
	
	public boolean isEditando() {
		boolean resultado = false;
		if (this.orcamento != null) {
			resultado = this.orcamento.getId() != null;
		}
		return resultado;
	}
}
