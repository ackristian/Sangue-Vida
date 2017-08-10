package com.salvavidas.repository.Filter;

import java.io.Serializable;

public class PacienteFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cidade;
	private String gsanguinio;

	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getGsanguinio() {
		return gsanguinio;
	}

	public void setGsanguinio(String gsanguinio) {
		this.gsanguinio = gsanguinio;
	}

}
