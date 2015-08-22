package sistema.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import sistema.financeiro.model.Lancamento;
import sistema.financeiro.repository.Lancamentos;
import sistema.financeiro.service.CadastroLancamentos;
import sistema.financeiro.service.NegocioException;


@Named
@javax.faces.view.ViewScoped
public class ConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Inject
	private Lancamentos lancamentosRepository;
	@Inject
	private CadastroLancamentos cadastro;
	
	private Lancamento lancamentoSelecionado;
	
	

	private List<Lancamento> lancamentos;
	
	public void consultar(){
		lancamentos = lancamentosRepository.todos();
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage(
			"Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
	
}
