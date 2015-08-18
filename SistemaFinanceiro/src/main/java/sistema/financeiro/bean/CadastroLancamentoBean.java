package sistema.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import sistema.financeiro.model.Lancamento;
import sistema.financeiro.model.Pessoa;
import sistema.financeiro.model.TipoLancamento;
import sistema.financeiro.repository.Lancamentos;
import sistema.financeiro.repository.Pessoas;
import sistema.financeiro.service.CadastroLancamentos;
import sistema.financeiro.service.NegocioException;
import sistema.financeiro.util.JpaUtil;


@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private Pessoas pessoas;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;
	
	
	public void prepararCadastro(){
		todasPessoas = pessoas.todas();
	}
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			cadastro.salvar(lancamento);
			lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		}catch(NegocioException e){
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		
	}
	
	public TipoLancamento[] getTiposLancamentos(){
		return TipoLancamento.values();
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public List<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}
	
	
}
