package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import repository.Orcamentos;
import model.Orcamento;

@FacesConverter(forClass = Orcamento.class)
public class OrcamentoConverter implements Converter{

	@Inject
	private Orcamentos orcamentos;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Orcamento retorno = null;

		if (arg2 != null && !"".equals(arg2)) {
			retorno = this.orcamentos.porId(new Long(arg2));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 != null) {
			Orcamento orcamento = (Orcamento) arg2;
			return orcamento.getId() == null ? null : orcamento.getId().toString();
		}
		
		return "";
	}

}
