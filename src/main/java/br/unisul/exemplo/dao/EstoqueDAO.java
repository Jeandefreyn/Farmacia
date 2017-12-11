package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.Estoque;
import br.unisul.exemplo.util.HibernateUtil;

public class EstoqueDAO implements DAOI<Estoque> {

	@Override
	public void inserir(Estoque entidade) {
		
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
	public List<Estoque> listar(Estoque entidade) {

		List<Estoque> lista = new ArrayList<Estoque>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Estoque.class);
			
			this.pesquisa(entidade, consulta);
			
//			consulta.addOrder(Order.asc("mercadoria.nome"));
			
			lista = consulta.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return lista;
	}

	@Override
	public void excluir(Estoque entidade) {

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
	public Estoque salvar(Estoque entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pesquisa(Estoque pesquisa, Criteria consulta) {
		
		if (pesquisa != null){
			
			if (pesquisa.getId() != null && pesquisa.getId() != 0)
				consulta.add(Restrictions.eq("id", pesquisa.getId()));
			
			if (pesquisa.getMercadoria().getId() != null && pesquisa.getMercadoria().getId() != 0)
				consulta.add(Restrictions.eq("mercadoria.id", pesquisa.getMercadoria().getId()));
			
			if (pesquisa.getLote().getId() != null && pesquisa.getLote().getId() != 0)
				consulta.add(Restrictions.eq("lote.id", pesquisa.getLote().getId()));
		}
	}

	@Override
	public Estoque selecionar(Estoque entidade) throws Exception {

		Estoque estoque = null;
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Estoque.class);
			
			this.pesquisa(entidade, consulta);
			
			estoque = (Estoque) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return estoque;
	}
}
