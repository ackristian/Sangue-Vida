package com.salvavidas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.salvavidas.model.Paciente;
import com.salvavidas.repository.Pacientes;
import com.salvavidas.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Paciente.class)
public class PacienteConverter implements Converter {
			
			@Inject
			private Pacientes pacientes;
			
			public PacienteConverter() {
				this.pacientes = (Pacientes) CDIServiceLocator.getBean(Pacientes.class);
			}
			
			@Override
			public Object getAsObject(FacesContext context, UIComponent component, String value) {
				Paciente retorno = null;
				
				if (value != null) {
					retorno = this.pacientes.porCodigo(new Long(value));
				}
				return retorno;
			}

			@Override
			public String getAsString(FacesContext context, UIComponent component, Object value) {
				if (value != null) {
					Long codigo = ((Paciente) value).getCodigo();
					String retorno = (codigo == null ? null : codigo.toString());
					
					return retorno;
			}
			return "";	
	}
}
