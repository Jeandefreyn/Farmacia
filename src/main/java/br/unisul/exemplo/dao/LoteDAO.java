package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.Lote;
import br.unisul.exemplo.util.HibernateUtil;

public class LoteDAO implements DAOI<Lote>{

	@Override
	public void inserir(Lote entidade) {
		
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
	public List<Lote> listar(Lote entidade) {

		List<Lote> lista = new ArrayList<Lote>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Lote.class);
			
			this.pesquisa(entidade, consulta);
			
			consulta.addOrder(Order.asc("lote"));
			
			lista = consulta.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return lista;
	}

	@Override
	public void excluir(Lote entidade) {

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
	public Lote salvar(Lote entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pesquisa(Lote pesquisa, Criteria consulta) {
		
		if (pesquisa != null){
			
			if (pesquisa.getMarca() != null && pesquisa.getMarca().getId() != null && pesquisa.getMarca().getId() != 0)
				consulta.add(Restrictions.eq("marca.id", pesquisa.getMarca().getId()));
			
			if (pesquisa.getLote() != null && !pesquisa.getLote().isEmpty())
				consulta.add(Restrictions.ilike("lote", pesquisa.getLote(), MatchMode.ANYWHERE));
			
			if (pesquisa.getId() != null)
				consulta.add(Restrictions.eq("id", pesquisa.getId()));
		}
	}

	@Override
	public Lote selecionar(Lote entidade) throws Exception {

		Lote lote = null;
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Lote.class);
			
			this.pesquisa(entidade, consulta);
			
			lote = (Lote) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return lote;
	}
}
