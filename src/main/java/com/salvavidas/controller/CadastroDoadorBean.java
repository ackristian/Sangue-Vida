package com.salvavidas.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.salvavidas.model.Doador;
import com.salvavidas.model.Provincia;
import com.salvavidas.model.Sexo;
import com.salvavidas.service.CadastroDoadorService;
import com.salvavidas.util.jsf.FacesUtil;
@Named
@ViewScoped
public class CadastroDoadorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Doador doador;
	
	@Inject
	private CadastroDoadorService cadastroDoadorService;

	private List<Sexo> sexos;
	private List<Provincia> provincias;
	
	private UploadedFile fotoDoador;
	
	
	
	@PostConstruct
	public void inicializar(){
		this.limpar();
		this.sexos = Arrays.asList(Sexo.values());
		this.provincias = Arrays.asList(Provincia.values());
		
	}
	
	public void salvar(){
		try {
			if (this.fotoDoador != null && this.fotoDoador.getContents() != null 
					&& this.fotoDoador.getContents().length > 0) {
				this.doador.setFoto(this.fotoDoador.getContents());
			}
			
			this.cadastroDoadorService.salvar(doador);
			FacesUtil.addSuccessMessage("Doador salvo com sucesso!");
		}
		catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());

		}
		this.limpar();
	}
		public void limpar() {
		this.doador = new Doador();
	}

		public Doador getDoador() {
			return doador;
		}

		public void setDoador(Doador doador) {
			this.doador = doador;
		}

		public CadastroDoadorService getCadastroDoadorService() {
			return cadastroDoadorService;
		}

		public void setCadastroDoadorService(CadastroDoadorService cadastroDoadorService) {
			this.cadastroDoadorService = cadastroDoadorService;
		}

		public UploadedFile getFotoDoador() {
			return fotoDoador;
		}

		public void setFotoDoador(UploadedFile fotoDoador) {
			this.fotoDoador = fotoDoador;
		}

		public List<Sexo> getSexos() {
			return sexos;
		}

		public List<Provincia> getProvincias() {
			return provincias;
		}
}