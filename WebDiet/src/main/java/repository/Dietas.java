package repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Dieta;

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
}
