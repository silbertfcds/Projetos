package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import repository.Alimentos;
import model.Alimento;

@FacesConverter(forClass = Alimento.class)
public class AlimentoConverter implements Converter {

	@Inject
	private Alimentos alimentos;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Alimento retorno = null;
		if(arg2!=null && !"".equals(arg2)){
			
			retorno = alimentos.porId(new Long(arg2));
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2!=null){
			Alimento alimento = (Alimento) arg2;
			return alimento.getId() == null ? null : alimento.getId().toString();
		}
		return "";
	}
}
