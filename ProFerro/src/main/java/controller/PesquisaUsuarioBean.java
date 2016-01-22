package controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Usuario;
import repository.Usuarios;
import repository.filter.UsuarioFilter;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean {

	private UsuarioFilter filtro;
	private List<Usuario> usuariosFiltrados;
	private Usuario usuarioSelecionado;
	
	@Inject
	private Usuarios usuarios;

	public PesquisaUsuarioBean() {
		filtro = new UsuarioFilter();
	}

	public void excluir(){
		usuarios.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome()+ " excluído com sucesso.");
	}
	
	public void pesquisar() {
		usuariosFiltrados = usuarios.filtrados(filtro);
		
		if(usuariosFiltrados.size()==0){
			FacesUtil.addErrorMessage("A busca não retornou nenhum item");
		}
	}
	
	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(UsuarioFilter filtro) {
		this.filtro = filtro;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
}
