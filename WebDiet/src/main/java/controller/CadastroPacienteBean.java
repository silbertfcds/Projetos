package controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.AvaliacaoAntropometrica;
import model.AvaliacaoBioquimica;
import model.ClassificacaoIMC;
import model.Endereco;
import model.Historico;
import model.Paciente;
import service.PacienteService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteService pacienteService;
	@Produces
	@PacienteEdicao
	private Paciente paciente;
	
	private AvaliacaoAntropometrica novaAvaliacaoAntropometrica;
	private AvaliacaoBioquimica novaAvaliacaoBioquimica;
	
	private AvaliacaoBioquimica avaliacaoBioquimicaSelecionada;
	private AvaliacaoAntropometrica avaliacaoAntropometricaSelecionada;

	public CadastroPacienteBean() {
		limpar();
	}
	
	public void limpar(){
		novaAvaliacaoBioquimica = new AvaliacaoBioquimica();
		novaAvaliacaoAntropometrica = new AvaliacaoAntropometrica();
		paciente = new Paciente();
		paciente.setEndereco(new Endereco());
		paciente.setHistorico(new Historico());
		paciente.getHistorico().setDoencas(new ArrayList<String>());
		paciente.getHistorico().setQueixas(new ArrayList<String>());
		paciente.setListaAvaliacaoAntropometrica(new ArrayList<AvaliacaoAntropometrica>());
		paciente.setListaAvaliacaoBioquimica(new ArrayList<AvaliacaoBioquimica>());
	}
	
	public void salvar() {
		paciente = pacienteService.salvar(paciente);
		//limpar();
		FacesUtil.addInfoMessage("Paciente adicionado com sucesso.");
	}
	
	public void adicionarAvaliacaoBioquimica(){
		novaAvaliacaoBioquimica = new AvaliacaoBioquimica();
	}
	
	public void adicionarAvaliacaoAntropometrica(){
		novaAvaliacaoAntropometrica = new AvaliacaoAntropometrica(); 
	}
	
	public void salvarAvaliacaoBioquimica(){
		novaAvaliacaoBioquimica.setData(new Date());
		paciente.getListaAvaliacaoBioquimica().add(novaAvaliacaoBioquimica);
		novaAvaliacaoBioquimica.setPaciente(paciente);
		FacesUtil.addInfoMessage("Avaliação Bioquímica adicionada com sucesso.");
	}
	
	public void removerAvaliacaoBioquimica(){
		this.paciente.getListaAvaliacaoBioquimica().remove(avaliacaoBioquimicaSelecionada);
		FacesUtil.addInfoMessage("Avaliação Bioquímica removida!");
	}
	
	public void salvarAvaliacaoAntropometrica(){
		novaAvaliacaoAntropometrica.setData(new Date());
		novaAvaliacaoAntropometrica.setImc(resultadoImc());
		if(novaAvaliacaoAntropometrica.getImc().doubleValue()!=0)
			novaAvaliacaoAntropometrica.setClassificacao(classificacaoImc(novaAvaliacaoAntropometrica.getImc()));
		paciente.getListaAvaliacaoAntropometrica().add(novaAvaliacaoAntropometrica);
		novaAvaliacaoAntropometrica.setPaciente(paciente);
		FacesUtil.addInfoMessage("Avaliação Antropométrica adicionada com sucesso.");
	}
	
	public void removerAvaliacaoAntropometrica(){
		this.paciente.getListaAvaliacaoAntropometrica().remove(avaliacaoAntropometricaSelecionada);
		FacesUtil.addInfoMessage("Avaliação Antropométrica removida!");
	}
	
	public BigDecimal resultadoImc(){
		BigDecimal altura = novaAvaliacaoAntropometrica.getAltura();
		BigDecimal peso = novaAvaliacaoAntropometrica.getPesoAtual();
		BigDecimal imc = BigDecimal.ZERO;
		if(!altura.equals(BigDecimal.ZERO) && !peso.equals(BigDecimal.ZERO))
			imc = peso.divide(altura.multiply(altura),2, RoundingMode.HALF_UP);
		
		return imc;
	}
	
	public ClassificacaoIMC classificacaoImc(BigDecimal imc){
		if(imc.doubleValue()<16){
			return ClassificacaoIMC.MAGREZA_GRAU_III;
		}else if(imc.doubleValue()>=16 && imc.doubleValue()<17){
			return ClassificacaoIMC.MAGREZA_GRAU_II;
		}else if(imc.doubleValue()>=17 && imc.doubleValue()<18.5){
			return ClassificacaoIMC.MAGREZA_GRAU_I;
		}else if(imc.doubleValue()>=18.5 && imc.doubleValue()<25){
			return ClassificacaoIMC.ADEQUADO;
		}else if(imc.doubleValue()>=25 && imc.doubleValue()<30){
			return ClassificacaoIMC.PRE_OBESO;
		}else if(imc.doubleValue()>=30 && imc.doubleValue()<35){
			return ClassificacaoIMC.OBESIDADE_GRAU_I;
		}else if(imc.doubleValue()>=35 && imc.doubleValue()<40){
			return ClassificacaoIMC.OBESIDADE_GRAU_II;
		}else{
			return ClassificacaoIMC.OBESIDADE_GRAU_III;
		}
	}
	
	public AvaliacaoAntropometrica getNovaAvaliacaoAntropometrica() {
		return novaAvaliacaoAntropometrica;
	}

	public void setNovaAvaliacaoAntropometrica(
			AvaliacaoAntropometrica novaAvaliacaoAntropometrica) {
		this.novaAvaliacaoAntropometrica = novaAvaliacaoAntropometrica;
	}

	public AvaliacaoBioquimica getNovaAvaliacaoBioquimica() {
		return novaAvaliacaoBioquimica;
	}

	public void setNovaAvaliacaoBioquimica(
			AvaliacaoBioquimica novaAvaliacaoBioquimica) {
		this.novaAvaliacaoBioquimica = novaAvaliacaoBioquimica;
	}

	public Paciente getPaciente() {
		if(paciente == null){
			paciente = new Paciente();
		}
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public AvaliacaoBioquimica getAvaliacaoBioquimicaSelecionada() {
		return avaliacaoBioquimicaSelecionada;
	}

	public void setAvaliacaoBioquimicaSelecionada(
			AvaliacaoBioquimica avaliacaoBioquimicaSelecionada) {
		this.avaliacaoBioquimicaSelecionada = avaliacaoBioquimicaSelecionada;
	}

	public AvaliacaoAntropometrica getAvaliacaoAntropometricaSelecionada() {
		return avaliacaoAntropometricaSelecionada;
	}

	public void setAvaliacaoAntropometricaSelecionada(
			AvaliacaoAntropometrica avaliacaoAntropometricaSelecionada) {
		this.avaliacaoAntropometricaSelecionada = avaliacaoAntropometricaSelecionada;
	}

}
