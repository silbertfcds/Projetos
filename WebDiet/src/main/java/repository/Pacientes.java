package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.Paciente;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import repository.filter.PacienteFilter;
import service.NegocioException;
import util.jpa.transactional;

public class Pacientes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Paciente porId(Long id) {
		return manager.find(Paciente.class, id);
	}
	
	public Paciente guardar(Paciente paciente){
		return manager.merge(paciente);
	}
	
	public Paciente porCpf(String documentoReceitaFederal){
		try {
			return manager.createQuery("from Paciente where documentoReceitaFederal = :documentoReceitaFederal",Paciente.class)
					.setParameter("documentoReceitaFederal", documentoReceitaFederal)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@transactional
	public void remover(Paciente paciente){
		try{
			paciente = porId(paciente.getId());
			manager.remove(paciente);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Paciente não pode ser excluído.");
		}
	}
	
	public List<Paciente> porNome(String nome){
		return manager.createQuery("from Paciente " +
				"where upper(nome) like :nome", Paciente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> filtrar(PacienteFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Paciente.class);
		
		if(StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())){
			criteria.add(Restrictions.ilike("documentoReceitaFederal", filtro.getDocumentoReceitaFederal(), 
					MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

}
