package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Grupo;
import model.Usuario;
import repository.Grupos;
import service.NegocioException;
import service.UsuarioService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private Grupos grupos;
	private Grupo novoGrupo;
	private Grupo removeGrupo;
	
	private List<Grupo> gruposRaizes;
	
	public UsuarioBean(){
		limpar();
	}
	
	public void salvar(){
		try {
            this.usuario = usuarioService.salvar(usuario);
            limpar();
            FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
        } catch (NegocioException e) {
            FacesUtil.addInfoMessage(e.getMessage());
        }
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			gruposRaizes = grupos.listar();
		}
	}
	
	public void adicionarGrupo(){
		
		if(usuario.getGrupos().contains(novoGrupo)){
			FacesUtil.addErrorMessage("Grupo já adicionado.");
		}else{
			usuario.getGrupos().add(novoGrupo);
		}
		
	}
	public void removerGrupo() { 
        this.usuario.getGrupos().remove(removeGrupo);
    }
	
	private void limpar() {
		usuario = new Usuario();
	}
	
	public boolean isEditando() {
        boolean resultado = false;
        if (this.usuario != null) {
            resultado = this.usuario.getId() != null;
        }
        return resultado;
	}
	
	public Usuario getUsuario() {
		if(usuario == null){
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Grupo> getGruposRaizes() {
		return gruposRaizes;
	}

	public void setGruposRaizes(List<Grupo> gruposRaizes) {
		this.gruposRaizes = gruposRaizes;
	}

	public Grupo getNovoGrupo() {
		return novoGrupo;
	}

	public void setNovoGrupo(Grupo novoGrupo) {
		this.novoGrupo = novoGrupo;
	}

	public Grupo getRemoveGrupo() {
		return removeGrupo;
	}

	public void setRemoveGrupo(Grupo removeGrupo) {
		this.removeGrupo = removeGrupo;
	}

}
