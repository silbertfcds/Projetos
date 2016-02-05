package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.Alimento;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import repository.filter.AlimentoFilter;
import service.NegocioException;
import util.jpa.transactional;

public class Alimentos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Alimento porId(Long id){
		return manager.find(Alimento.class, id);
	}
	
	public List<Alimento> porDescricao(String descricao){
		return manager.createQuery("from Alimento " +
				"where upper(descricao) like :descricao", Alimento.class)
				.setParameter("descricao", descricao.toUpperCase() + "%")
				.getResultList();
	}
	
	public Alimento porNome(String nome){
		try {
			return manager.createQuery("from Alimento where upper(descricao) = :descricao", Alimento.class)
					.setParameter("descricao", nome.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Alimento guardar(Alimento alimento){
		return manager.merge(alimento);
	}

	@transactional
	public void remover(Alimento alimento){
		try{
			alimento = porId(alimento.getId());
			manager.remove(alimento);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Alimento não pode ser excluído.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Alimento> filtrar(AlimentoFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Alimento.class);
		
		if(StringUtils.isNotBlank(filtro.getDescricao())){
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), 
					MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("descricao")).list();
	}
	
}
