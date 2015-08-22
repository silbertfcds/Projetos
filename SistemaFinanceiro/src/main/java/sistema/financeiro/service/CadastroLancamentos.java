package sistema.financeiro.service;

import java.io.Serializable;
import java.util.Date;






import javax.inject.Inject;


import sistema.financeiro.model.Lancamento;
import sistema.financeiro.repository.Lancamentos;
import sistema.financeiro.util.transactional;

public class CadastroLancamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Lancamentos lancamentos;
	
	@transactional
	public void salvar(Lancamento lancamento) throws NegocioException{
		if(lancamento.getDataPagamento()!=null && lancamento.getDataPagamento().after(new Date())){
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");
		}
			lancamentos.guardar(lancamento);
	}
	
	@transactional
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentos.porId(lancamento.getId());
		if(lancamento.getDataPagamento()!=null){
			throw new NegocioException("Não é possivel excluir um lançamento pago!");
			
		}
		this.lancamentos.remover(lancamento);
	}
}
