package com.salvavidas.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.salvavidas.model.Paciente;
import com.salvavidas.repository.Filter.PacienteFilter;
import com.salvavidas.service.NegocioException;
import com.salvavidas.util.jpa.Transactional;


public class Pacientes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Paciente guardar(Paciente paciente){
		
		return manager.merge(paciente);	
	}
	
	@Transactional
	public void remover(Paciente paciente){
		
		try {
			paciente = porCodigo(paciente.getCodigo());
			manager.remove(paciente);
			manager.flush();
			
		} catch (PersistenceException e) {
			throw new NegocioException("O Paciente não pode ser excluido!!!");
		}	
	}
	
public Paciente porCodigo(Long codigo) {
		return manager.find(Paciente.class, codigo);
	}
	
	public List<Paciente> porProvincia(String provincia) {
		return this.manager.createQuery("from Paciente " +
				"where upper(provincia) like :provincia", Paciente.class)
				.setParameter("provincia", provincia.toUpperCase() + "%")
				.getResultList();
	}

	public Paciente porGuposanguinio(String grupo) {
			try{
			return manager.createQuery("from Paciente where upper(gsanguinio) = :gsanguinio",Paciente.class)
					.setParameter("gsanguinio", grupo.toUpperCase())
					.getSingleResult();
				}catch(NoResultException e){
					return null;
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> buscarTodos() {
	return manager.createQuery("Select d from Paciente d")
			.getResultList(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> filtrados(PacienteFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Paciente.class);
		
			if(StringUtils.isNotBlank(filtro.getGsanguinio())){
				criteria.add(Restrictions.eq("gsanguinio", filtro.getGsanguinio()));
			}
		   if(StringUtils.isNotBlank(filtro.getCidade())){
			   criteria.add(Restrictions.ilike("cidade", filtro.getCidade(),MatchMode.ANYWHERE));
		   }
		   
		   return criteria.addOrder(Order.asc("nome")).list();
	}

	public Paciente porBi(String bi) {
		try{
			return manager.createQuery("from Paciente where upper(bi) = :bi",Paciente.class)
					.setParameter("bi", bi.toUpperCase())
					.getSingleResult();
				}catch(NoResultException e){
					
					return null;
			}
		
	}
}
