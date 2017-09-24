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

import com.salvavidas.model.Doador;
import com.salvavidas.repository.Filter.DoadorFilter;
import com.salvavidas.service.NegocioException;
import com.salvavidas.util.jpa.Transactional;



public class Doadores implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Doador guardar(Doador doador){
		
		return manager.merge(doador);	
	}
	
	@Transactional
	public void remover(Doador doador){
		
		try {
			doador = porCodigo(doador.getCodigo());
			manager.remove(doador);
			manager.flush();
			
		} catch (PersistenceException e) {
			throw new NegocioException("O doador não pode ser excluido!!!");
		}	
	}
	
public Doador porCodigo(Long codigo) {
		
		return manager.find(Doador.class, codigo);
	}
	
	public List<Doador> porProvincia(String provincia) {
		return this.manager.createQuery("from Doador " +
				"where upper(provincia) like :provincia", Doador.class)
				.setParameter("provincia", provincia.toUpperCase() + "%")
				.getResultList();
	}

	public Doador porGuposanguinio(String grupo) {
			try{
			return manager.createQuery("from Doador where upper(gsanguinio) = :gsanguinio",Doador.class)
					.setParameter("gsanguinio", grupo.toUpperCase())
					.getSingleResult();
				}catch(NoResultException e){
					return null;
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<Doador> buscarTodos() {
	return manager.createQuery("Select d from Doador d")
			.getResultList(); 
	}
	
	public Doador porBi(String bi) {
		try{
			return manager.createQuery("from Doador where upper(bi) = :bi",Doador.class)
					.setParameter("bi", bi.toUpperCase())
					.getSingleResult();
				}catch(NoResultException e){
					
					return null;
			}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Doador> filtrados(DoadorFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Doador.class);
		
			if(StringUtils.isNotBlank(filtro.getGsanguinio())){
				criteria.add(Restrictions.eq("gsanguinio", filtro.getGsanguinio()));
			}
		   if(StringUtils.isNotBlank(filtro.getCidade())){
			   criteria.add(Restrictions.ilike("cidade", filtro.getCidade(),MatchMode.ANYWHERE));
		   }
		   
		   if(StringUtils.isNotBlank(filtro.getBi())){
			   criteria.add(Restrictions.eq("bi", filtro.getBi()));
		   }
		   
		   return criteria.addOrder(Order.asc("nome")).list();
	}

	
}
