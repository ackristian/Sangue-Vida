package com.salvavidas.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.salvavidas.model.Doador;
import com.salvavidas.repository.Doadores;
import com.salvavidas.util.jpa.Transactional;

public class CadastroDoadorService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Doadores doadores;
	
	@Transactional
	public Doador salvar(Doador doador){
		if (doador.getGsanguinio() == null || doador.getGsanguinio().trim().equals("")) { 
			throw new NegocioException("O grupo sangunio do Doador é obrigatório");
		}
		
		Doador doadorExistente = doadores.porBi(doador.getBi());
		if(doadorExistente !=null && !doadorExistente.equals(doador)){
			throw new NegocioException("Já existe um Doador com o número de B.I informado.");
		}
		
		return doadores.guardar(doador);	
	}
}
