package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import model.Dieta;
import repository.Dietas;

@FacesConverter(forClass = Dieta.class)
public class DietaConverter implements Converter {

	@Inject
	private Dietas dietas;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Dieta retorno = null;
		if(arg2!=null && !"".equals(arg2)){
			
			retorno = dietas.porId(new Long(arg2));
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2!=null){
			Dieta dieta = (Dieta) arg2;
			return dieta.getId() == null ? null : dieta.getId().toString();
		}
		return "";
	}
}
