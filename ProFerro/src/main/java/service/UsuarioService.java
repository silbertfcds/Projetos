package service;

import java.io.Serializable;

import javax.inject.Inject;

import model.Usuario;
import repository.Usuarios;
import util.jpa.transactional;

public class UsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@transactional
	public Usuario salvar(Usuario usuario){
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
		
		if(usuarioExistente!=null && !usuarioExistente.equals(usuario)){
			throw new NegocioException("Já existe um Usuário com o email informado.");
		}
		
		
		return usuarios.guardar(usuario);
	}
}
