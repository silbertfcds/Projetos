package repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Evento;
import util.jpa.transactional;

public class Eventos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@transactional
	public Evento guardar(Evento evento) {
		
		return manager.merge(evento);
	}
	
	public List<Evento> listar() {
		return manager.createQuery("from Evento",
				Evento.class).getResultList();
	}

	public Evento porId(Long id) {
		return manager.find(Evento.class, id);
	}
}
