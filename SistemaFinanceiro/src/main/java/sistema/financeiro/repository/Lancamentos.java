package sistema.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sistema.financeiro.model.Lancamento;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public Lancamentos(EntityManager manager){
		this.manager = manager;
	}
	
	public void adicionar(Lancamento lancamento){
		manager.persist(lancamento);
	}
	public List<Lancamento> todos(){
			TypedQuery<Lancamento> query = manager.createQuery("from Lancamento",Lancamento.class);
		return query.getResultList();
	}
}
