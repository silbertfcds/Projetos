package sistema.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sistema.financeiro.model.Lancamento;
import sistema.financeiro.repository.Lancamentos;
import sistema.financeiro.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {

	private static long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos;
	
	public void consultar(){
		EntityManager manager = JpaUtil.getEntityManager();
		Lancamentos lancamentos = new Lancamentos(manager);
		this.lancamentos = lancamentos.todos();
		manager.close();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	
}
