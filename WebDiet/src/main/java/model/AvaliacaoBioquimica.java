package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "avalicao_bioquimica")
public class AvaliacaoBioquimica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_avaliacao_bioquimica")
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_avaliacao", nullable = false)
	private Date data;
	
	private String glicose;
	private String hematocrito;
	private String hemoglobina;
	private String colesterolTotal;
	private String colesterolLdl;
	private String colesterolHdl;
	private String triglicerideos;
	private String ureia;
	private String creatinina;
	private String acidoUrico;
	private String tgo;
	private String tgp;
	private String hemoglobinaGlicada;
	private String leucocitos;
	private String linfocitos;
	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false)
	private Paciente paciente;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGlicose() {
		return glicose;
	}

	public void setGlicose(String glicose) {
		this.glicose = glicose;
	}

	public String getHematocrito() {
		return hematocrito;
	}

	public void setHematocrito(String hematocrito) {
		this.hematocrito = hematocrito;
	}

	public String getHemoglobina() {
		return hemoglobina;
	}

	public void setHemoglobina(String hemoglobina) {
		this.hemoglobina = hemoglobina;
	}

	public String getColesterolTotal() {
		return colesterolTotal;
	}

	public void setColesterolTotal(String colesterolTotal) {
		this.colesterolTotal = colesterolTotal;
	}

	public String getColesterolLdl() {
		return colesterolLdl;
	}

	public void setColesterolLdl(String colesterolLdl) {
		this.colesterolLdl = colesterolLdl;
	}

	public String getColesterolHdl() {
		return colesterolHdl;
	}

	public void setColesterolHdl(String colesterolHdl) {
		this.colesterolHdl = colesterolHdl;
	}

	public String getTriglicerideos() {
		return triglicerideos;
	}

	public void setTriglicerideos(String triglicerideos) {
		this.triglicerideos = triglicerideos;
	}

	public String getUreia() {
		return ureia;
	}

	public void setUreia(String ureia) {
		this.ureia = ureia;
	}

	public String getCreatinina() {
		return creatinina;
	}

	public void setCreatinina(String creatinina) {
		this.creatinina = creatinina;
	}

	public String getAcidoUrico() {
		return acidoUrico;
	}

	public void setAcidoUrico(String acidoUrico) {
		this.acidoUrico = acidoUrico;
	}

	public String getTgo() {
		return tgo;
	}

	public void setTgo(String tgo) {
		this.tgo = tgo;
	}

	public String getTgp() {
		return tgp;
	}

	public void setTgp(String tgp) {
		this.tgp = tgp;
	}

	public String getHemoglobinaGlicada() {
		return hemoglobinaGlicada;
	}

	public void setHemoglobinaGlicada(String hemoglobinaGlicada) {
		this.hemoglobinaGlicada = hemoglobinaGlicada;
	}

	public String getLeucocitos() {
		return leucocitos;
	}

	public void setLeucocitos(String leucocitos) {
		this.leucocitos = leucocitos;
	}

	public String getLinfocitos() {
		return linfocitos;
	}

	public void setLinfocitos(String linfocitos) {
		this.linfocitos = linfocitos;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
		AvaliacaoBioquimica other = (AvaliacaoBioquimica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
