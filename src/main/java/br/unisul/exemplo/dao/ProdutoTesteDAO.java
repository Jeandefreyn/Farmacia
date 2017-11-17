package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.ProdutoTeste;
import br.unisul.exemplo.util.HibernateUtil;

public class ProdutoTesteDAO implements DAOI<ProdutoTeste> {

	@Override
	public void inserir(ProdutoTeste entidade) {
		
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
	
	@Override
	public ProdutoTeste salvar(ProdutoTeste entidade){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		ProdutoTeste novo = null;
		
		try {
			
			t = sessao.beginTransaction();
			novo = (ProdutoTeste) sessao.merge(entidade);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ProdutoTeste> listar(ProdutoTeste filtro) {

		List<ProdutoTeste> lista = new ArrayList<ProdutoTeste>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(ProdutoTeste.class);
			
			if (filtro.getNome() != null && !filtro.getNome().isEmpty())
				consulta.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			
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
	public void excluir(ProdutoTeste entidade) {

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
	public void pesquisa(ProdutoTeste pesquisa, Criteria consulta) {
		// TODO Auto-generated method stub
		
	}
	
	public ProdutoTeste buscarPorID(Long codigo) throws Exception {
		
		ProdutoTeste produto = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			
			Criteria consulta = sessao.createCriteria(ProdutoTeste.class);
			consulta.add(Restrictions.idEq(codigo));
			produto = (ProdutoTeste) consulta.uniqueResult();
			
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return produto;
	}

	@Override
	public ProdutoTeste selecionar(ProdutoTeste entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
