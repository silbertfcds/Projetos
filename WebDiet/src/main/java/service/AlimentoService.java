package service;

import java.io.Serializable;

import javax.inject.Inject;

import repository.Alimentos;
import model.Alimento;
import util.jpa.transactional;

public class AlimentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Alimentos alimentos;
	
	@transactional
	public Alimento salvar(Alimento alimento){
		Alimento alimentoExistente = alimentos.porNome(alimento.getDescricao());
		
		if(alimentoExistente!=null && !alimentoExistente.equals(alimento)){
			throw new NegocioException("Já existe um Alimento com o NOME informado.");
		}
		
		return alimentos.guardar(alimento);
	}

}
