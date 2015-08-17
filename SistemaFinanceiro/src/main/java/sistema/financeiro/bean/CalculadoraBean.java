package sistema.financeiro.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CalculadoraBean {

	private Double valorA;
	private Double valorB;
	private Double resultado;
	
	public void somar(){
		resultado = valorA+valorB;
	}
	public Double getValorA() {
		return valorA;
	}
	public void setValorA(Double valorA) {
		this.valorA = valorA;
	}
	public Double getValorB() {
		return valorB;
	}
	public void setValorB(Double valorB) {
		this.valorB = valorB;
	}
	public Double getResultado() {
		return resultado;
	}
	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}
	
	
}
