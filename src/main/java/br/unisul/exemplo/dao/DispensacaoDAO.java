package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.Dispensacao;
import br.unisul.exemplo.util.HibernateUtil;

public class DispensacaoDAO implements DAOI<Dispensacao> {

	@Override
	public void inserir(Dispensacao entidade) {
		
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
	public List<Dispensacao> listar(Dispensacao entidade) {

		List<Dispensacao> lista = new ArrayList<Dispensacao>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Dispensacao.class);
			
			this.pesquisa(entidade, consulta);
			
			lista = consulta.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return lista;
	}

	@Override
	public void excluir(Dispensacao entidade) {

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
	public Dispensacao salvar(Dispensacao entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pesquisa(Dispensacao pesquisa, Criteria consulta) {
		
		if (pesquisa != null){
			
			if (pesquisa.getId() != null)
				consulta.add(Restrictions.eq("id", pesquisa.getId()));
		}
	}

	@Override
	public Dispensacao selecionar(Dispensacao entidade) throws Exception {

		Dispensacao dispensacao = null;
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Dispensacao.class);
			
			this.pesquisa(entidade, consulta);
			
			dispensacao = (Dispensacao) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return dispensacao;
	}
}
