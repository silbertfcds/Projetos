package security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Grupo;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {
	
	@Inject
	private ExternalContext externalContext;
	
	
	/** Retorna o nome do usuário logado */
	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}
		return nome;
	}

	/**
	 * Verifica se o usuário logado é do grupo Administrador
	 * @return Boolean
	 */
	public boolean isAdministrador(){
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		for (Grupo u : usuarioLogado.getUsuario().getGrupos()) {
			if(u.getDescricao().equals("ADMINISTRADOR")){
				return true;
			}
		}
		return false;
	}
	
	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario;

	}

}
