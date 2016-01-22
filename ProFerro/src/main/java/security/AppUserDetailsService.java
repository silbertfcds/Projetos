package security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Grupo;
import model.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import repository.Usuarios;
import util.CDIServiceLocator;

/*
 *Bean de serviço do Spring Security
 */
public class AppUserDetailsService implements UserDetailsService {

	//@Inject
	//private Usuarios usuarios;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioSistema user = null;
		//Não é possivel injetar porque essa classe vai ser um bean do spring e não um bean do contexto
		Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class); 
		Usuario usuario = usuarios.porEmail(email); 
		
		if(usuario!=null){
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		return user;
	}

	/** Preenche a lista de grupos do usuário logado */  
	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		
		return authorities;

	}

}
