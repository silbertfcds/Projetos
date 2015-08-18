package sistema.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sistema.financeiro.model.Lancamento;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public Lancamentos(EntityManager manager){
		this.manager = manager;
	}
	
	public void adicionar(Lancamento lancamento){
		EntityTransaction trx = this.manager.getTransaction();
		trx.begin();
		manager.persist(lancamento);
		trx.commit();
	}
	public List<Lancamento> todos(){
			TypedQuery<Lancamento> query = manager.createQuery("from Lancamento",Lancamento.class);
		return query.getResultList();
	}
}
