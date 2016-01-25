package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Orcamento;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import repository.filter.OrcamentoFilter;
import util.jsf.FacesUtil;

public class Orcamentos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	
	public Orcamento guardar(Orcamento orcamento){
		return manager.merge(orcamento);
	}
	
	@SuppressWarnings("unchecked")
	public List<Orcamento> filtrados(OrcamentoFilter filtro) {
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Orcamento.class)
				// fazemos uma associação (join) com cliente e nomeamos como "c"
				.createAlias("cliente", "c");
				

		if (filtro.getDataCriacaoDe() != null) {
			criteria.add(Restrictions.ge("criacao", FacesUtil.configurarTempoDataDe(filtro.getDataCriacaoDe())));
		}
		
		if (filtro.getDataCriacaoAte() != null) {
			criteria.add(Restrictions.le("criacao", FacesUtil.configurarTempoDataAte(filtro.getDataCriacaoAte())));
		}
		
		if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
			// acessamos o nome do cliente associado ao pedido pelo alias "c", criado anteriormente
			criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
		}
		return criteria.addOrder(Order.desc("criacao")).list();
	}

	public Orcamento porId(Long id) {
		
		return manager.find(Orcamento.class, id);
	}
	
}


