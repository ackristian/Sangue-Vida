package com.salvavidas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.salvavidas.model.Doador;
import com.salvavidas.repository.Doadores;
import com.salvavidas.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Doador.class)
public class DoadorConverter implements Converter {

			private Doadores doadores;
			
			public DoadorConverter() {
				this.doadores = (Doadores) CDIServiceLocator.getBean(Doadores.class);
			}
			
			@Override
			public Object getAsObject(FacesContext context, UIComponent component, String value) {
				Doador retorno = null;
				
				if (value != null) {
					retorno = this.doadores.porCodigo(new Long(value));
				}
				return retorno;
			}

			@Override
			public String getAsString(FacesContext context, UIComponent component, Object value) {
				if (value != null) {
					Long codigo = ((Doador) value).getCodigo();
					String retorno = (codigo == null ? null : codigo.toString());
					
					return retorno;
			}
			return "";	
	}
}
