package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="orcamento")
public class Orcamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	private Date criacao;
	@NotNull
	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorTotal;
	@NotNull
	@ManyToOne
	@JoinColumn(name ="cliente_id", nullable = false)
	private Cliente cliente;
	@NotBlank
	@Column(nullable = false, length = 20)
	private String descricao;
	@OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemOrcamento> itens = new ArrayList<ItemOrcamento>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemOrcamento> getItens() {
		return itens;
	}

	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		Orcamento other = (Orcamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void recalcularValorTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (ItemOrcamento item : this.getItens()) {
			if (item.getId() != null) {
				total = total.add(item.getValorTotal());
			}else{
				total = total.add(item.getValorTotal());
			}
		}
		
		this.setValorTotal(total);
	}
	
	@Transient
	public boolean isNovo(){
		return getId() ==null;
	}
	
	@Transient
	public boolean valorTotalNegativo() {
		return getValorTotal().compareTo(BigDecimal.ZERO)<0;
	}
}
