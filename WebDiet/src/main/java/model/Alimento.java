package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="alimento")
public class Alimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_alimento")
	private Long id;
	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	private String descricao;
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "descricaoComplementar")
	private String descricaoComplementar;
	@NotNull(message = "é obrigatório")
	@Column(name="caloria", nullable = false, precision = 10, scale = 2)
	private BigDecimal caloria = BigDecimal.ZERO;
	@NotNull(message = "é obrigatório")
	@Column(name="quantidade_padrao", nullable = false, precision = 10, scale = 0)
	private BigDecimal quantidadePadrao = BigDecimal.ONE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getCaloria() {
		return caloria;
	}

	public void setCaloria(BigDecimal caloria) {
		this.caloria = caloria;
	}

	public BigDecimal getQuantidadePadrao() {
		return quantidadePadrao;
	}

	public void setQuantidadePadrao(BigDecimal quantidadePadrao) {
		this.quantidadePadrao = quantidadePadrao;
	}

	public String getDescricaoComplementar() {
		return descricaoComplementar;
	}

	public void setDescricaoComplementar(String descricaoComplementar) {
		this.descricaoComplementar = descricaoComplementar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caloria == null) ? 0 : caloria.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		Alimento other = (Alimento) obj;
		if (caloria == null) {
			if (other.caloria != null)
				return false;
		} else if (!caloria.equals(other.caloria))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
		return descricao;
	}

}
