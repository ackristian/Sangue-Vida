package com.salvavidas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.salvavidas.model.Doador;
import com.salvavidas.repository.Doadores;
import com.salvavidas.repository.Pacientes;
import com.salvavidas.repository.Filter.DoadorFilter;
import com.salvavidas.service.NegocioException;
import com.salvavidas.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaDoadorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Doadores doadores;
	
	@Inject
	private Pacientes pacientes;
	
	private DoadorFilter filtro;
	private List<Doador> doadoresFiltrados;
	private Doador doadorSelecionado;
	private int totalDoadores = 0;
	private int totalPacientes = 0;
	
	
	@PostConstruct
	public void inicializar(){
		
			try {
				this.totalDoadores = doadores.buscarTodos().size();
				this.totalPacientes = pacientes.buscarTodos().size();
		
			} catch (Exception e) {
				FacesUtil.addErrorMessage(e.getMessage());
			
			}
			
		}
		
	
	
	public PesquisaDoadorBean(){
		filtro = new DoadorFilter();
	}
	
	public void excluir(){
		try {
			this.doadores.remover(doadorSelecionado);
			this.doadoresFiltrados.remove(doadorSelecionado);
			FacesUtil.addSuccessMessage("Doador " + doadorSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void pesquisar(){
		doadoresFiltrados = doadores.filtrados(filtro);
	}

	public Doador getDoadorSelecionado() {
		return doadorSelecionado;
	}

	public void setDoadorSelecionado(Doador doadorSelecionado) {
		this.doadorSelecionado = doadorSelecionado;
	}

	public DoadorFilter getFiltro() {
		return filtro;
	}

	public List<Doador> getDoadoresFiltrados() {
		return doadoresFiltrados;
	}
	
	public int getTotalDoadores() {
		return totalDoadores;
	}

	public void setTotalDoadores(int totalDoadores) {
		this.totalDoadores = totalDoadores;
	}

	public int getTotalPacientes() {
		return totalPacientes;
	}

	public void setTotalPacientes(int totalPacientes) {
		this.totalPacientes = totalPacientes;
	}
	
}
