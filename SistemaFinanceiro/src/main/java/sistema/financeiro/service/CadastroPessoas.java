package sistema.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import sistema.financeiro.model.Pessoa;
import sistema.financeiro.repository.Pessoas;
import sistema.financeiro.util.transactional;

public class CadastroPessoas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoas pessoas;
	
	@transactional
	public void salvar(Pessoa pessoa)throws NegocioException{
		if(pessoa.getNome().equals(new Pessoa())){
			throw new NegocioException("Pessoa já existe.");
		}
		pessoas.guardar(pessoa);
	}
	
	@transactional
	public void excluir(Pessoa pessoa){
		pessoa = pessoas.porId(pessoa.getId());
		pessoas.remover(pessoa);
	}
	
}
