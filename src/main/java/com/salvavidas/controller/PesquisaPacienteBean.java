package com.salvavidas.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.salvavidas.model.Paciente;
import com.salvavidas.repository.Pacientes;
import com.salvavidas.repository.Filter.PacienteFilter;
import com.salvavidas.service.NegocioException;
import com.salvavidas.util.jsf.FacesUtil;
@Named
@ViewScoped
public class PesquisaPacienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pacientes pacientes;
	
	private PacienteFilter filtro;
	private List<Paciente> pacientesFiltrados;
	private Paciente pacienteSelecionado;
	
	
	public PesquisaPacienteBean(){
		filtro = new PacienteFilter();
	}
	
	public void excluir(){
		try {
			this.pacientes.remover(pacienteSelecionado);
			this.pacientesFiltrados.remove(pacienteSelecionado);
			FacesUtil.addSuccessMessage("Paciente " + pacienteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void pesquisar(){
		pacientesFiltrados = pacientes.filtrados(filtro);
	}

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public PacienteFilter getFiltro() {
		return filtro;
	}

	public List<Paciente> getPacientesFiltrados() {
		return pacientesFiltrados;
	}
}
