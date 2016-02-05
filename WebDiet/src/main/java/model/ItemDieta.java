package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_dieta")
public class ItemDieta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@NotNull(message = "é obrigatório")
	@Column(name = "caloria", nullable = false, precision = 10, scale = 2)
	private double caloria;
	@ManyToOne
	@JoinColumn(name = "alimento_id", nullable = false)
	private Alimento alimento;
	@ManyToOne
	@JoinColumn(name = "dieta_id", nullable = false)
	private Dieta dieta;
	@Column(nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private Horario horario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCaloria() {
		return caloria;
	}

	public void setCaloria(double caloria) {
		this.caloria = caloria;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alimento == null) ? 0 : alimento.hashCode());
		long temp;
		temp = Double.doubleToLongBits(caloria);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dieta == null) ? 0 : dieta.hashCode());
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
		if (Double.doubleToLongBits(caloria) != Double
				.doubleToLongBits(other.caloria))
			return false;
		if (dieta == null) {
			if (other.dieta != null)
				return false;
		} else if (!dieta.equals(other.dieta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
