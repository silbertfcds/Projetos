package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_dieta")
public class ItemDieta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_item_dieta")
	private Long id;
	@Column(name = "caloria_total_item", nullable = false, precision = 10, scale = 2)
	private BigDecimal caloriaTotalItem = BigDecimal.ZERO;
	@Column(name = "quantidade", nullable = false, precision = 10, scale = 0)
	private BigDecimal quantidade = BigDecimal.ONE;
	@ManyToOne
	@JoinColumn(name = "id_alimento", nullable = false)
	private Alimento alimento;
	@ManyToOne
	@JoinColumn(name = "id_dieta", nullable = false)
	private Dieta dieta;
	@Column(nullable = false, length = 15)
	@Enumerated(EnumType.STRING)
	private Horario horario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCaloriaTotalItem() {
		return caloriaTotalItem;
	}

	public void setCaloriaTotalItem(BigDecimal caloriaTotalItem) {
		this.caloriaTotalItem = caloriaTotalItem;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public void getTotalCaloriaItem(){
		BigDecimal total = BigDecimal.ZERO;
		total = getAlimento().getCaloria().multiply(getQuantidade()).divide(getAlimento().getQuantidadePadrao());
		setCaloriaTotalItem(total);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alimento == null) ? 0 : alimento.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
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
		ItemDieta other = (ItemDieta) obj;
		if (alimento == null) {
			if (other.alimento != null)
				return false;
		} else if (!alimento.equals(other.alimento))
			return false;
		if (horario != other.horario)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getAlimento().toString();
	}
}
