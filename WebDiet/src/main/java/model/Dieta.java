package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dieta")
public class Dieta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_dieta")
	private Long id;
	@NotNull
	@Column(name = "total_caloria", nullable = false, precision = 10, scale = 2)
	private BigDecimal totalCalorias = BigDecimal.ZERO;
	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false)
	private Paciente paciente;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	private Date criacao;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio", nullable = false)
	private Date dataInicio;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino", nullable = false)
	private Date dataTermino;
	
	@OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemDieta> itens = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotalCalorias() {
		return totalCalorias;
	}

	public void setTotalCalorias(BigDecimal totalCalorias) {
		this.totalCalorias = totalCalorias;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public List<ItemDieta> getItens() {
		return itens;
	}

	public void setItens(List<ItemDieta> itens) {
		this.itens = itens;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
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
		Dieta other = (Dieta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void calcularTotalCalorias(){
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal caloria = BigDecimal.ZERO;
		
		for(ItemDieta item: itens){
			caloria = item.getAlimento().getCaloria();
			item.setCaloria(caloria);
			total.add(item.getCaloria());
		}
		
		setTotalCalorias(total);
	}
	
	@Transient
	public boolean isNovo(){
		return getId()==null;
	}
	
	@Transient
	public boolean isEditando(){
		return!isNovo();
	}
}
