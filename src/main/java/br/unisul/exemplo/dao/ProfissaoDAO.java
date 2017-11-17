package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.Profissao;
import br.unisul.exemplo.util.HibernateUtil;

public class ProfissaoDAO implements DAOI<Profissao> {

	@Override
	public void inserir(Profissao entidade) {
		
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
	public List<Profissao> listar(Profissao entidade) {

		List<Profissao> lista = new ArrayList<Profissao>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Profissao.class);
			
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
	public void excluir(Profissao entidade) {

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
	public Profissao salvar(Profissao entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pesquisa(Profissao pesquisa, Criteria consulta) {
		
		if (pesquisa != null){
			
			if (pesquisa.getCbo() != null && !pesquisa.getCbo().isEmpty())
				consulta.add(Restrictions.eq("cbo", pesquisa.getCbo()));
			
			if (pesquisa.getNome() != null && !pesquisa.getNome().isEmpty())
				consulta.add(Restrictions.ilike("nome", pesquisa.getNome(), MatchMode.ANYWHERE));
			
			if (pesquisa.getId() != null)
				consulta.add(Restrictions.eq("id", pesquisa.getId()));
		}
		
	}

	@Override
	public Profissao selecionar(Profissao entidade) throws Exception {

		Profissao profissao = null;
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Profissao.class);
			
			this.pesquisa(entidade, consulta);
			
			profissao = (Profissao) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return profissao;
	}
}
