package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Alimento;
import model.Dieta;
import model.Horario;
import model.ItemDieta;
import model.Paciente;
import repository.Alimentos;
import repository.Pacientes;
import service.DietaService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroDietaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Dieta dieta;

	private Alimento alimento;

	@Inject
	private DietaService service;
	
	@Inject
	private Alimentos alimentos;
	
	@Inject
	private Pacientes pacientes;
	
	private ItemDieta novoItem;
	private ItemDieta itemSelecionado;
	
	private List<ItemDieta> listaCafe = new ArrayList<ItemDieta>();
	private List<ItemDieta> listaLancheManha = new ArrayList<ItemDieta>();
	private List<ItemDieta> listaAlmoco = new ArrayList<ItemDieta>();
	private List<ItemDieta> listaLancheTarde = new ArrayList<ItemDieta>();
	private List<ItemDieta> listaJanta = new ArrayList<ItemDieta>();
	private List<ItemDieta> listaCeia = new ArrayList<ItemDieta>();
	

	public CadastroDietaBean() {
		limpar();
	}
	
	public void salvar(){
		service.salvar(dieta);
		FacesUtil.addInfoMessage("Dieta salva com sucesso. ");
		limpar();
	}

	private void classificacaoItem(ItemDieta item){
		if(item.getHorario().getDescricao().equals("Cafe")){
			listaCafe.add(item);
		}else if(item.getHorario().getDescricao().equals("Lanche Manha")){
			listaLancheManha.add(item);
		}else if(item.getHorario().getDescricao().equals("Almoco")){
			listaAlmoco.add(item);
		}else if(item.getHorario().getDescricao().equals("Lanche Tarde")){
			listaLancheTarde.add(item);
		}else if(item.getHorario().getDescricao().equals("Jantar")){
			listaJanta.add(item);
		}else{
			listaCeia.add(item);
		}
 	}
	
	private void removerClassificacaoItem(ItemDieta item){
		if(item.getHorario().getDescricao().equals("Cafe")){
			listaCafe.remove(item);
		}else if(item.getHorario().getDescricao().equals("Lanche Manha")){
			listaLancheManha.remove(item);
		}else if(item.getHorario().getDescricao().equals("Almoco")){
			listaAlmoco.remove(item);
		}else if(item.getHorario().getDescricao().equals("Lanche Tarde")){
			listaLancheTarde.remove(item);
		}else if(item.getHorario().getDescricao().equals("Jantar")){
			listaJanta.remove(item);
		}else{
			listaCeia.remove(item);
		}
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			if(this.dieta==null){
				this.dieta = new Dieta();
			}
			carregarListasAlimentos();
		}
	}
	
	public void carregarListasAlimentos(){
		if(dieta.getItens().size()!=0){
			for(ItemDieta item: dieta.getItens()){
				classificacaoItem(item);
			}
		}
	}
	
	public void adicionarAlimento(){
		if(!novoItem.getAlimento().getDescricao().equals("")){
			classificacaoItem(novoItem);
			dieta.getItens().add(novoItem);
			//dieta.calcularTotalCalorias();
			novoItem.getTotalCaloriaItem();
			dieta.calcularTotalCaloriasDieta();
			novoItem.setDieta(dieta);
			novoItem =  new ItemDieta();
			FacesUtil.addInfoMessage("Item adicionado.");
		}else{
			FacesUtil.addErrorMessage("Preencha os campos corretamente.");
		}
	}

	public void excluir(){
		dieta.getItens().remove(itemSelecionado);
		removerClassificacaoItem(itemSelecionado);
		FacesUtil.addInfoMessage("Item removido.");
		
	}
	
	public List<Alimento> completarAlimento(String descricao){
		return  alimentos.porDescricao(descricao);
	}
	
	public List<Paciente> completarPaciente(String descricao){
		return pacientes.porNome(descricao);
	}
	
	public void limpar(){
		dieta = new Dieta();
		novoItem = new ItemDieta();
		
		listaCafe = new ArrayList<ItemDieta>();
		listaLancheManha = new ArrayList<ItemDieta>();
		listaAlmoco = new ArrayList<ItemDieta>();
		listaLancheTarde = new ArrayList<ItemDieta>();
		listaJanta = new ArrayList<ItemDieta>();
		listaCeia = new ArrayList<ItemDieta>();
	}
	
	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public ItemDieta getNovoItem() {
		return novoItem;
	}

	public void setNovoItem(ItemDieta novoItem) {
		this.novoItem = novoItem;
	}

	public Horario[] getHorarios(){
		return Horario.values();
	}

	public List<ItemDieta> getListaCafe() {
		return listaCafe;
	}

	public void setListaCafe(List<ItemDieta> listaCafe) {
		this.listaCafe = listaCafe;
	}

	public List<ItemDieta> getListaLancheManha() {
		return listaLancheManha;
	}

	public void setListaLancheManha(List<ItemDieta> listaLancheManha) {
		this.listaLancheManha = listaLancheManha;
	}

	public List<ItemDieta> getListaAlmoco() {
		return listaAlmoco;
	}

	public void setListaAlmoco(List<ItemDieta> listaAlmoco) {
		this.listaAlmoco = listaAlmoco;
	}

	public List<ItemDieta> getListaLancheTarde() {
		return listaLancheTarde;
	}

	public void setListaLancheTarde(List<ItemDieta> listaLancheTarde) {
		this.listaLancheTarde = listaLancheTarde;
	}

	public List<ItemDieta> getListaJanta() {
		return listaJanta;
	}

	public void setListaJanta(List<ItemDieta> listaJanta) {
		this.listaJanta = listaJanta;
	}

	public List<ItemDieta> getListaCeia() {
		return listaCeia;
	}

	public void setListaCeia(List<ItemDieta> listaCeia) {
		this.listaCeia = listaCeia;
	}

	public ItemDieta getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(ItemDieta itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}
	
}
