package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.Paciente;
import br.unisul.exemplo.util.HibernateUtil;

public class PacienteDAO implements DAOI<Paciente>{

	@Override
	public void inserir(Paciente entidade) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		
		try {
			
			t = sessao.beginTransaction();
			sessao.merge(entidade);
			t.commit();
			
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			
			throw e;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> listar(Paciente entidade) {

		List<Paciente> lista = new ArrayList<Paciente>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Paciente.class);
			
			this.pesquisa(entidade, consulta);
			
			consulta.addOrder(Order.asc("nome"));
			
			lista = consulta.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return lista;
	}

	@Override
	public void excluir(Paciente entidade) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		
		try {
			
			t = sessao.beginTransaction();
			sessao.delete(entidade);
			t.commit();
			
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			
			throw e;
		} finally {
			sessao.close();
		}
	}

	@Override
	public Paciente salvar(Paciente entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pesquisa(Paciente pesquisa, Criteria consulta) {
		
		if (pesquisa != null){
			
			if (pesquisa.getNome() != null && !pesquisa.getNome().isEmpty())
				consulta.add(Restrictions.ilike("nome", pesquisa.getNome(), MatchMode.ANYWHERE));
			
			if (pesquisa.getId() != null)
				consulta.add(Restrictions.eq("id", pesquisa.getId()));
		}
	}

	@Override
	public Paciente selecionar(Paciente entidade) throws Exception {

		Paciente paciente = null;
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Paciente.class);
			
			this.pesquisa(entidade, consulta);
			
			paciente = (Paciente) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return paciente;
	}
}
