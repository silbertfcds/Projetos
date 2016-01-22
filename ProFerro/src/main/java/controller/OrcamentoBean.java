package controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.ItemOrcamento;
import model.Orcamento;

import org.hibernate.Session;

import repository.Clientes;
import service.OrcamentoService;
import util.jsf.FacesUtil;
import util.report.ExecutorRelatorio;

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
	
	private ItemOrcamento novoItem;
	
	private ItemOrcamento removeItem;
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;
	
	
	
	public OrcamentoBean(){
		limpar();
	}

	public void imprimir() {
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("numero", orcamento.getId());
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/orcamento/relatorioOrcamento.jasper",
				this.response, parametros, "Orcamento.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados");
		}
	}
	
	public void salvar(){
		service.salvar(orcamento);
		FacesUtil.addInfoMessage("Orçamento salvo com sucesso.");
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
		novoItem = new ItemOrcamento();
	}
	
	public void adicionarItem(){
		if(!novoItem.getDescricao().equals("")){
			orcamento.getItens().add(novoItem);
			novoItem.setOrcamento(orcamento);
			orcamento.recalcularValorTotal();
			novoItem = new ItemOrcamento();
			FacesUtil.addInfoMessage("Item adicionado.");
		}else{
			FacesUtil.addErrorMessage("Campo Vazio");
		}
	}
	
	public void excluirItem(){
		orcamento.getItens().remove(removeItem);
		orcamento.recalcularValorTotal();
		FacesUtil.addInfoMessage("Item excluído");
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

	public ItemOrcamento getNovoItem() {
		return novoItem;
	}

	public void setNovoItem(ItemOrcamento novoItem) {
		this.novoItem = novoItem;
	}

	public ItemOrcamento getRemoveItem() {
		return removeItem;
	}

	public void setRemoveItem(ItemOrcamento removeItem) {
		this.removeItem = removeItem;
	}

}
