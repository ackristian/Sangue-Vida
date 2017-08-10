package com.salvavidas.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.salvavidas.model.Paciente;
import com.salvavidas.model.Provincia;
import com.salvavidas.model.Sexo;
import com.salvavidas.service.CadastroPacienteService;
import com.salvavidas.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPacienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Paciente paciente;
	
	@Inject
	private CadastroPacienteService cadastroPacienteService;
	
	private List<Sexo> sexos;
	private List<Provincia> provincias;
	
	private UploadedFile fotoPaciente;
	
	@PostConstruct
	public void inicializar(){
		this.limpar();
		this.sexos = Arrays.asList(Sexo.values());
		this.provincias = Arrays.asList(Provincia.values());
	}
	
	public void salvar(){
		try {
			if (this.fotoPaciente != null && this.fotoPaciente.getContents() != null 
					&& this.fotoPaciente.getContents().length > 0) {
				this.paciente.setFoto(this.fotoPaciente.getContents());
			}
			
			this.cadastroPacienteService.salvar(paciente);
			FacesUtil.addSuccessMessage("paciente salvo com sucesso!");
		}
		catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());

		}
		this.limpar();
	}
		public void limpar() {
		this.paciente = new Paciente();
	}

		public UploadedFile getFotoPaciente() {
			return fotoPaciente;
		}

		public void setFotoPaciente(UploadedFile fotoPaciente) {
			this.fotoPaciente = fotoPaciente;
		}

		public Paciente getPaciente() {
			return paciente;
		}
		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

		public CadastroPacienteService getCadastroPacienteService() {
			return cadastroPacienteService;
		}

		public List<Sexo> getSexos() {
			return sexos;
		}

		public List<Provincia> getProvincias() {
			return provincias;
		}
		
}
