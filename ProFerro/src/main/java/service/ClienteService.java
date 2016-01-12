package service;

import java.io.Serializable;

import javax.inject.Inject;

import model.Cliente;
import repository.Clientes;
import util.jpa.transactional;

public class ClienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	@transactional
	public Cliente salvar(Cliente cliente){
		Cliente clienteExistente = clientes.porCpfCnpj(cliente.getDocumentoReceitaFederal());
		
		if(clienteExistente!=null && !clienteExistente.equals(cliente)){
			throw new NegocioException("Já existe um Cliente com o CPF/CNPJ informado.");
		}
		
		
		return clientes.guardar(cliente);
	}

}
