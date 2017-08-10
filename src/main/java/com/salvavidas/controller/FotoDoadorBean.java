package com.salvavidas.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.salvavidas.model.Doador;

@Named
@SessionScoped

public class FotoDoadorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Doador doadorSelecionado;
	
	public StreamedContent getFoto(){
		DefaultStreamedContent content = null;
		if (this.doadorSelecionado != null && this.doadorSelecionado.getFoto() != null
				&& this.doadorSelecionado.getFoto().length > 0) {
			byte[] imagem = this.doadorSelecionado.getFoto();
			content = new DefaultStreamedContent(new ByteArrayInputStream(imagem), "image/jpg", "doador.jpg");
		}
		
		return content;
	}

	public Doador getDoadorSelecionado() {
		return doadorSelecionado;
	}

	public void setDoadorSelecionado(Doador doadorSelecionado) {
		this.doadorSelecionado = doadorSelecionado;
	}
	
}
