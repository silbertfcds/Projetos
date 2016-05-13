package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import model.Dieta;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import repository.filter.DietaFilter;
import service.NegocioException;
import util.jpa.transactional;
import util.jsf.FacesUtil;

public class Dietas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Dieta porId(Long id){
		return manager.find(Dieta.class, id);
	}
	
	public Dieta guardar(Dieta dieta){
		return manager.merge(dieta);
	}
	
	@transactional
	public void remover(Dieta dieta){
		try{
			dieta = porId(dieta.getId());
			manager.remove(dieta);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Dieta não pode ser excluído.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Dieta> filtrados(DietaFilter filtro) {
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Dieta.class)
				// fazemos uma associação (join) com paciente e nomeamos como "p"
				.createAlias("paciente", "p");
				

		if (filtro.getCadastroDe() != null) {
			criteria.add(Restrictions.ge("criacao", FacesUtil.configurarTempoDataDe(filtro.getCadastroDe())));
		}
		
		if (filtro.getCadastroAte() != null) {
			criteria.add(Restrictions.le("criacao", FacesUtil.configurarTempoDataAte(filtro.getCadastroAte())));
		}
		
		if (StringUtils.isNotBlank(filtro.getNomePaciente())) {
			// acessamos o nome do cliente associado ao pedido pelo alias "c", criado anteriormente
			criteria.add(Restrictions.ilike("p.dadosPessoais.nome", filtro.getNomePaciente(), MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.desc("criacao")).list();
	}
	
	public int getNextNumero() {
		Query query = (Query) manager.createQuery(
				"SELECT " + "COALESCE(MAX(numero)+1 ,1) "
						+ "FROM dieta ");

		return (Integer) query.uniqueResult();
	}
}
