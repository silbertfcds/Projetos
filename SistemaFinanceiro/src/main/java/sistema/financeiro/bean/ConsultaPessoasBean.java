package sistema.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sistema.financeiro.model.Pessoa;
import sistema.financeiro.repository.Pessoas;
import sistema.financeiro.service.CadastroPessoas;


@Named
@ViewScoped
public class ConsultaPessoasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPessoas cadastro;
	@Inject
	private Pessoas pessoasRepository;
	
	private Pessoa pessoaSelecionado;
	
	private List<Pessoa> pessoas;
	
	public void consultar(){
		pessoas = pessoasRepository.todas();
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.cadastro.excluir(this.pessoaSelecionado);
		this.consultar();
		context.addMessage(null, new FacesMessage(
		"Pessoa excluída com sucesso!"));
	
	}

	public Pessoa getPessoaSelecionado() {
		return pessoaSelecionado;
	}

	public void setPessoaSelecionado(Pessoa pessoaSelecionado) {
		this.pessoaSelecionado = pessoaSelecionado;
	}
	
	
}
