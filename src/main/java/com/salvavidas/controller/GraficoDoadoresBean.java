package com.salvavidas.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import com.salvavidas.model.Doador;
import com.salvavidas.repository.Doadores;
import com.salvavidas.repository.Filter.DoadorFilter;

@Named
@RequestScoped
public class GraficoDoadoresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Doadores doadores;

	private DoadorFilter filtro;
	private List<Doador> doadoresFiltrados;
	private Doador doadorSelecionado;

	private PieChartModel teste;

	@PostConstruct
	public void init() {
		graficoPizza();
	}

	public void graficoPizza() {

		PieChartModel graficoPizza = new PieChartModel();

		List<Doador> todos = doadores.buscarTodos();

		Map<String, Long> map = todos.stream().collect(
				Collectors.groupingBy(Doador::getGsanguinio,
						Collectors.counting()));

		for (Entry<String, Long> entry : map.entrySet()) {
			graficoPizza.set(entry.getKey(), entry.getValue());
		}

		graficoPizza.setTitle("Resultados da Pesquisa");
		graficoPizza.setLegendPosition("w");
		graficoPizza.setShowDataLabels(true);
		graficoPizza.setSeriesColors("E7E658,1a85ba,66cc66");
		graficoPizza.setDiameter(230);
		graficoPizza.setDataFormat("percent");

		teste = graficoPizza;
	}

	public PieChartModel getTeste() {
		return teste;
	}

	public void setTeste(PieChartModel teste) {
		this.teste = teste;
	}

	public DoadorFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(DoadorFilter filtro) {
		this.filtro = filtro;
	}

	public List<Doador> getDoadoresFiltrados() {
		return doadoresFiltrados;
	}

	public void setDoadoresFiltrados(List<Doador> doadoresFiltrados) {
		this.doadoresFiltrados = doadoresFiltrados;
	}

	public Doador getDoadorSelecionado() {
		return doadorSelecionado;
	}

	public void setDoadorSelecionado(Doador doadorSelecionado) {
		this.doadorSelecionado = doadorSelecionado;
	}

	public Doadores getDoadores() {
		return doadores;
	}

	public void setDoadores(Doadores doadores) {
		this.doadores = doadores;
	}

}
