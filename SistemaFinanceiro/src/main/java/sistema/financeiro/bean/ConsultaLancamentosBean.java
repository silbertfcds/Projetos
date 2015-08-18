package sistema.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sistema.financeiro.model.Lancamento;
import sistema.financeiro.repository.Lancamentos;
import sistema.financeiro.util.JpaUtil;

@Named
@javax.faces.view.ViewScoped
public class ConsultaLancamentosBean implements Serializable {

	private static long serialVersionUID = 1L;
	
	@Inject
	private Lancamentos lancamentosRepository;
	
	private List<Lancamento> lancamentos;
	
	public void consultar(){
		lancamentos = lancamentosRepository.todos();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	
}
