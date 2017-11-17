package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.Endereco;
import br.unisul.exemplo.util.HibernateUtil;

public class EnderecoDAO implements DAOI<Endereco> {

	@Override
	public void inserir(Endereco entidade) {
		
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
	public List<Endereco> listar(Endereco entidade) {

		List<Endereco> lista = new ArrayList<Endereco>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Endereco.class);
			
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
	public void excluir(Endereco entidade) {

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
	public Endereco salvar(Endereco entidade) throws Exception {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction t = null;
		Endereco novo = null;
		
		try {
			
			t = sessao.beginTransaction();
			novo = (Endereco) sessao.merge(entidade);
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
	public void pesquisa(Endereco pesquisa, Criteria consulta) {

		if (pesquisa != null){
			
			if (pesquisa.getId() != null)
				consulta.add(Restrictions.eq("id", pesquisa.getId()));
			
			if (pesquisa.getCep() != null)
				consulta.add(Restrictions.eq("cep", pesquisa.getCep()));
		}
	}

	@Override
	public Endereco selecionar(Endereco entidade) throws Exception {

		Endereco endereco = null;
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Endereco.class);
			
			this.pesquisa(entidade, consulta);
			
			endereco = (Endereco) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return endereco;
	}
}
