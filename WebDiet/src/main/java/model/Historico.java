package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "historico")
public class Historico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private boolean atividadeFisica;
	@Column(length = 60)
	private String tipoAtividadeFisica;
	@Column(length = 20)
	private String frequenciaAtividadeFisica;
	@Column(length = 20)
	private String duracaoAtividadeFisica;
	@Column(length = 20)
	private String horaSono;

	@ElementCollection
	@CollectionTable(name="queixa", joinColumns=@JoinColumn(name="historico_id"))
	@Column(name="queixa_name")
	private List<String> queixas = new ArrayList<String>();
	@Transient
	private String[] arrayQueixas;
	@ElementCollection
	@CollectionTable(name="doenca", joinColumns=@JoinColumn(name="historico_id"))
	@Column(name="doenca_name")
	private List<String> doencas = new ArrayList<String>();
	@Transient
	private String[] arrayHistoricoFamiliar;
	@Column(length = 150)
	private String outrasDoencas;
	
	private boolean suplemento;
	@Column(length = 20)
	private String descricaoSuplemento;
	@Column(length = 20)
	private String tempoSuplemento;

	private boolean fumante;
	@Column(length = 20)
	private String tempoFumante;

	private boolean bebida;
	@Column(length = 20)
	private String tipoBebida;
	@Column(length = 20)
	private String frequenciaBebida;
	@Column(length = 150)
	private String medicamento;
	private boolean tratamentoDietetico;
	@Column(length = 150)
	private String objetivoConsulta;
	
	
	public boolean isAtividadeFisica() {
		return atividadeFisica;
	}

	public void setAtividadeFisica(boolean atividadeFisica) {
		this.atividadeFisica = atividadeFisica;
	}

	public String getTipoAtividadeFisica() {
		return tipoAtividadeFisica;
	}

	public void setTipoAtividadeFisica(String tipoAtividadeFisica) {
		this.tipoAtividadeFisica = tipoAtividadeFisica;
	}

	public String getFrequenciaAtividadeFisica() {
		return frequenciaAtividadeFisica;
	}

	public void setFrequenciaAtividadeFisica(String frequenciaAtividadeFisica) {
		this.frequenciaAtividadeFisica = frequenciaAtividadeFisica;
	}

	public String getDuracaoAtividadeFisica() {
		return duracaoAtividadeFisica;
	}

	public void setDuracaoAtividadeFisica(String duracaoAtividadeFisica) {
		this.duracaoAtividadeFisica = duracaoAtividadeFisica;
	}

	public boolean isSuplemento() {
		return suplemento;
	}

	public void setSuplemento(boolean suplemento) {
		this.suplemento = suplemento;
	}

	public String getDescricaoSuplemento() {
		return descricaoSuplemento;
	}

	public void setDescricaoSuplemento(String descricaoSuplemento) {
		this.descricaoSuplemento = descricaoSuplemento;
	}

	public String getTempoSuplemento() {
		return tempoSuplemento;
	}

	public void setTempoSuplemento(String tempoSuplemento) {
		this.tempoSuplemento = tempoSuplemento;
	}

	public boolean isFumante() {
		return fumante;
	}

	public void setFumante(boolean fumante) {
		this.fumante = fumante;
	}

	public String getTempoFumante() {
		return tempoFumante;
	}

	public void setTempoFumante(String tempoFumante) {
		this.tempoFumante = tempoFumante;
	}

	public boolean isBebida() {
		return bebida;
	}

	public void setBebida(boolean bebida) {
		this.bebida = bebida;
	}

	public String getTipoBebida() {
		return tipoBebida;
	}

	public void setTipoBebida(String tipoBebida) {
		this.tipoBebida = tipoBebida;
	}

	public String getFrequenciaBebida() {
		return frequenciaBebida;
	}

	public void setFrequenciaBebida(String frequenciaBebida) {
		this.frequenciaBebida = frequenciaBebida;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public boolean isTratamentoDietetico() {
		return tratamentoDietetico;
	}

	public void setTratamentoDietetico(boolean tratamentoDietetico) {
		this.tratamentoDietetico = tratamentoDietetico;
	}

	public String getObjetivoConsulta() {
		return objetivoConsulta;
	}

	public void setObjetivoConsulta(String objetivoConsulta) {
		this.objetivoConsulta = objetivoConsulta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHoraSono() {
		return horaSono;
	}

	public void setHoraSono(String horaSono) {
		this.horaSono = horaSono;
	}

	public List<String> getQueixas() {
		return queixas;
	}

	public void setQueixas(List<String> queixas) {
		this.queixas = queixas;
	}

	public String[] getArrayQueixas() {
		return arrayQueixas;
	}

	public void setArrayQueixas(String[] arrayQueixas) {
		this.arrayQueixas = arrayQueixas;
	}

	public List<String> getDoencas() {
		return doencas;
	}

	public void setDoencas(List<String> doencas) {
		this.doencas = doencas;
	}

	public String[] getArrayHistoricoFamiliar() {
		return arrayHistoricoFamiliar;
	}

	public void setArrayHistoricoFamiliar(String[] arrayHistoricoFamiliar) {
		this.arrayHistoricoFamiliar = arrayHistoricoFamiliar;
	}

	public String getOutrasDoencas() {
		return outrasDoencas;
	}

	public void setOutrasDoencas(String outrasDoencas) {
		this.outrasDoencas = outrasDoencas;
	}
	
	
}
