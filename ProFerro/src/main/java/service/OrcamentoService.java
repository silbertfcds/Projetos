package service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import model.Orcamento;
import repository.Orcamentos;
import util.jpa.transactional;

public class OrcamentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Orcamentos orcamentos;
	
	@transactional
	public Orcamento salvar(Orcamento orcamento){
		if(orcamento.isNovo()){
			orcamento.setCriacao(new Date());
		
		}
		
		orcamento.recalcularValorTotal();
		
		if(orcamento.getItens().isEmpty()){
			throw new NegocioException("O orçamento deve possuir pelo menos um item.");
		}
		
		if(orcamento.valorTotalNegativo()){
			throw new NegocioException("O valor total do pedido não pode ser negativo.");
		}
		return orcamentos.guardar(orcamento);
		
	}
}
