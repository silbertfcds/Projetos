package sistema.financeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="lancamento")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@ManyToOne(optional=false)
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	@NotEmpty
	@Size(max=80)
	@Column(length=80, nullable=false)
	private String descricao;
	@NotNull
	@DecimalMin("0")
	@Column(precision=10,scale=2,nullable=false)
	private BigDecimal valor;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoLancamento tipo;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name="data_vencimento",nullable=false)
	private Date dataVencimento;
	@Temporal(TemporalType.DATE)
	@Column(name="data_pagamento",nullable=true)
	private Date dataPagamento;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoLancamento getTipo() {
		return tipo;
	}
	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	
}
