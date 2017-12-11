package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.Mercadoria;
import br.unisul.exemplo.util.HibernateUtil;

public class MercadoriaDAO implements DAOI<Mercadoria> {

	@Override
	public void inserir(Mercadoria entidade) {
		
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
	public List<Mercadoria> listar(Mercadoria entidade) {

		List<Mercadoria> lista = new ArrayList<Mercadoria>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Mercadoria.class);
			
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
	public void excluir(Mercadoria entidade) {

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
	public Mercadoria salvar(Mercadoria entidade) throws Exception {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		Mercadoria novo = null;
		
		try {
			
			t = sessao.beginTransaction();
			novo = (Mercadoria) sessao.merge(entidade);
			t.commit();
			
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			
			throw e;
		} finally {
			sessao.close();
		}
		
		return novo;
	}

	@Override
	public void pesquisa(Mercadoria pesquisa, Criteria consulta) {
		
		if (pesquisa != null){
			
			if (pesquisa.getNome() != null && !pesquisa.getNome().isEmpty())
				consulta.add(Restrictions.ilike("nome", pesquisa.getNome(), MatchMode.ANYWHERE));
			
			if (pesquisa.getCodigoBarra() != null && !pesquisa.getCodigoBarra().isEmpty())
				consulta.add(Restrictions.eq("codigoBarra", pesquisa.getCodigoBarra()));
			
			if (pesquisa.getId() != null)
				consulta.add(Restrictions.eq("id", pesquisa.getId()));
		}
	}

	@Override
	public Mercadoria selecionar(Mercadoria entidade) throws Exception {

		Mercadoria mercadoria = null;
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Mercadoria.class);
			
			this.pesquisa(entidade, consulta);
			
			mercadoria = (Mercadoria) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return mercadoria;
	}
}
