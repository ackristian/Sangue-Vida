package com.salvavidas.repository.Filter;

import java.io.Serializable;

public class DoadorFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cidade;
	private String gsanguinio;
	private String bi;

	public String getBi() {
		return bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}

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
