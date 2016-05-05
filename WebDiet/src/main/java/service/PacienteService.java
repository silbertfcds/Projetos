package service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import model.Paciente;
import repository.Pacientes;
import util.jpa.transactional;

public class PacienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pacientes pacientes;
	
	@transactional
	public Paciente salvar(Paciente paciente){
		Paciente pacienteExistente = pacientes.porCpf(paciente.getDadosPessoais().getDocumentoReceitaFederal());
		
		if(pacienteExistente!=null && !pacienteExistente.equals(paciente)){
			throw new NegocioException("Já existe um Paciente com o CPF/CNPJ informado.");
		}
		//paciente.getDadosPessoais().setDataNascimento(new Date());
		return pacientes.guardar(paciente);
	}

}
