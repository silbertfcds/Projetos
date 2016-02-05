package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import model.Paciente;
import repository.Pacientes;

@FacesConverter(forClass = Paciente.class)
public class PacienteConverter implements Converter {

	@Inject
	private Pacientes pacientes;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Paciente retorno = null;
		if(arg2!=null && !"".equals(arg2)){
			
			retorno = pacientes.porId(new Long(arg2));
			
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2!=null){
			Paciente paciente = (Paciente) arg2;
			return paciente.getId() == null ? null : paciente.getId().toString();
		}
		return "";
	}

}
