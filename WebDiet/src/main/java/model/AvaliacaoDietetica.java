package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "avalicao_dietetica")
public class AvaliacaoDietetica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id_avaliacao_dietetica")
	private Long id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_avaliacao", nullable = false)
	private Date data;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String desjejum;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "lanche_manha")
	private String lancheManha;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String almoco;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "lanche_tarde")
	private String lancheTarde;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String jantar;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String ceia;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "preferencias_alimentares")
	private String preferenciasAlimentares;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "aversoes_alimentares")
	private String aversoesAlimentares;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "alimentacao_final_semana")
	private String alimentacaoFinalSemana;
	
	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false)
	private Paciente paciente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDesjejum() {
		return desjejum;
	}

	public void setDesjejum(String desjejum) {
		this.desjejum = desjejum;
	}

	public String getLancheManha() {
		return lancheManha;
	}

	public void setLancheManha(String lancheManha) {
		this.lancheManha = lancheManha;
	}

	public String getAlmoco() {
		return almoco;
	}

	public void setAlmoco(String almoco) {
		this.almoco = almoco;
	}

	public String getLancheTarde() {
		return lancheTarde;
	}

	public void setLancheTarde(String lancheTarde) {
		this.lancheTarde = lancheTarde;
	}

	public String getJantar() {
		return jantar;
	}

	public void setJantar(String jantar) {
		this.jantar = jantar;
	}

	public String getCeia() {
		return ceia;
	}

	public void setCeia(String ceia) {
		this.ceia = ceia;
	}

	public String getPreferenciasAlimentares() {
		return preferenciasAlimentares;
	}

	public void setPreferenciasAlimentares(String preferenciasAlimentares) {
		this.preferenciasAlimentares = preferenciasAlimentares;
	}

	public String getAversoesAlimentares() {
		return aversoesAlimentares;
	}

	public void setAversoesAlimentares(String aversoesAlimentares) {
		this.aversoesAlimentares = aversoesAlimentares;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getAlimentacaoFinalSemana() {
		return alimentacaoFinalSemana;
	}

	public void setAlimentacaoFinalSemana(String alimentacaoFinalSemana) {
		this.alimentacaoFinalSemana = alimentacaoFinalSemana;
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
		AvaliacaoDietetica other = (AvaliacaoDietetica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
