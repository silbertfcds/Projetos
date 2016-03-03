package service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import model.Dieta;
import repository.Dietas;
import util.jpa.transactional;

public class DietaService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Dietas dietas;
	
	@transactional
	public Dieta salvar(Dieta dieta){
		if(dieta.isNovo()){
			dieta.setCriacao(new Date());
		}
		
		return dietas.guardar(dieta);
	}
}
