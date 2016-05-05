package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "avalicao_antropometrica")
public class AvaliacaoAntropometrica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_avaliacao_antropometrica")
	private Long id;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_avaliacao", nullable = false)
	private Date data;

	@Column(precision = 10, scale = 2)
	private BigDecimal altura;
	@Column(precision = 10, scale = 3)
	private BigDecimal pesoAtual;
	@Column(precision = 10, scale = 2)
	private BigDecimal imc;
	@Column(precision = 10, scale = 2)
	private BigDecimal gordura;
	@Column(precision = 10, scale = 2)
	private BigDecimal massaMagra;
	@Column(precision = 10, scale = 2)
	private BigDecimal circunferenciaBraco;
	@Column(precision = 10, scale = 2)
	private BigDecimal circunferenciaPanturrilha;
	@Column(precision = 10, scale = 2)
	private BigDecimal dobraSubescapular;
	@Column(precision = 10, scale = 2)
	private BigDecimal dobraBiceps;
	@Column(precision = 10, scale = 2)
	private BigDecimal dobraTriciptal;
	@Column(precision = 10, scale = 2)
	private BigDecimal dobraSuprailiaca;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getPesoAtual() {
		return pesoAtual;
	}

	public void setPesoAtual(BigDecimal pesoAtual) {
		this.pesoAtual = pesoAtual;
	}

	public BigDecimal getImc() {
		return imc;
	}

	public void setImc(BigDecimal imc) {
		this.imc = imc;
	}

	public BigDecimal getGordura() {
		return gordura;
	}

	public void setGordura(BigDecimal gordura) {
		this.gordura = gordura;
	}

	public BigDecimal getMassaMagra() {
		return massaMagra;
	}

	public void setMassaMagra(BigDecimal massaMagra) {
		this.massaMagra = massaMagra;
	}

	public BigDecimal getCircunferenciaBraco() {
		return circunferenciaBraco;
	}

	public void setCircunferenciaBraco(BigDecimal circunferenciaBraco) {
		this.circunferenciaBraco = circunferenciaBraco;
	}

	public BigDecimal getCircunferenciaPanturrilha() {
		return circunferenciaPanturrilha;
	}

	public void setCircunferenciaPanturrilha(
			BigDecimal circunferenciaPanturrilha) {
		this.circunferenciaPanturrilha = circunferenciaPanturrilha;
	}

	public BigDecimal getDobraSubescapular() {
		return dobraSubescapular;
	}

	public void setDobraSubescapular(BigDecimal dobraSubescapular) {
		this.dobraSubescapular = dobraSubescapular;
	}

	public BigDecimal getDobraBiceps() {
		return dobraBiceps;
	}

	public void setDobraBiceps(BigDecimal dobraBiceps) {
		this.dobraBiceps = dobraBiceps;
	}

	public BigDecimal getDobraTriciptal() {
		return dobraTriciptal;
	}

	public void setDobraTriciptal(BigDecimal dobraTriciptal) {
		this.dobraTriciptal = dobraTriciptal;
	}

	public BigDecimal getDobraSuprailiaca() {
		return dobraSuprailiaca;
	}

	public void setDobraSuprailiaca(BigDecimal dobraSuprailiaca) {
		this.dobraSuprailiaca = dobraSuprailiaca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvaliacaoAntropometrica other = (AvaliacaoAntropometrica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}