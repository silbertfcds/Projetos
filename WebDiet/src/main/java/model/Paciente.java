package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_paciente")
	private Long id;
	
	@Embedded
	private DadosPessoais dadosPessoais = new DadosPessoais();
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	@JoinColumn(name="id_historico")
	private Historico historico = new Historico();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_endereco")
	private Endereco endereco = new Endereco();
	
	@OneToMany(mappedBy = "paciente",  cascade = CascadeType.ALL, orphanRemoval=true)
	private List<AvaliacaoBioquimica> listaAvaliacaoBioquimica = new ArrayList<AvaliacaoBioquimica>();
	
	@OneToMany(mappedBy = "paciente",  cascade = CascadeType.ALL, orphanRemoval=true)
	private List<AvaliacaoAntropometrica> listaAvaliacaoAntropometrica = new ArrayList<AvaliacaoAntropometrica>();
	
	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<AvaliacaoBioquimica> getListaAvaliacaoBioquimica() {
		return listaAvaliacaoBioquimica;
	}

	public void setListaAvaliacaoBioquimica(
			List<AvaliacaoBioquimica> listaAvaliacaoBioquimica) {
		this.listaAvaliacaoBioquimica = listaAvaliacaoBioquimica;
	}
	
	public List<AvaliacaoAntropometrica> getListaAvaliacaoAntropometrica() {
		return listaAvaliacaoAntropometrica;
	}

	public void setListaAvaliacaoAntropometrica(
			List<AvaliacaoAntropometrica> listaAvaliacaoAntropometrica) {
		this.listaAvaliacaoAntropometrica = listaAvaliacaoAntropometrica;
	}

	@Transient
	public boolean isNovo(){
		return getId()==null;
	}
	
	@Transient
	public boolean isEditando(){
		return !isNovo();
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
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
