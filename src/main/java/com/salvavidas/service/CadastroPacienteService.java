package com.salvavidas.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.salvavidas.model.Paciente;
import com.salvavidas.repository.Pacientes;
import com.salvavidas.util.jpa.Transactional;

public class CadastroPacienteService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Pacientes pacientes;
	
	@Transactional
	public Paciente salvar(Paciente paciente){
		if (paciente.getGsanguinio() == null || paciente.getGsanguinio().trim().equals("")) { 
			throw new NegocioException("O grupo sangunio do Paciente é obrigatório");
		}
		
		Paciente pacienteExistente = pacientes.porBi(paciente.getBi());
		if(pacienteExistente !=null && !pacienteExistente.equals(paciente)){
			throw new NegocioException("Já existe um Paciente com o número de B.I informado.");
		}
		
		return pacientes.guardar(paciente);	
	}
}
