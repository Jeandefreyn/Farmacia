package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.Fabricante;
import br.unisul.exemplo.util.HibernateUtil;

public class FabricanteDAO implements DAOI<Fabricante> {

	@Override
	public void inserir(Fabricante entidade) {
		
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
	public List<Fabricante> listar(Fabricante entidade) {

		List<Fabricante> lista = new ArrayList<Fabricante>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Fabricante.class);
			
			this.pesquisa(entidade, consulta);
			
			consulta.addOrder(Order.asc("nomeFantasia"));
			
			lista = consulta.list();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return lista;
	}

	@Override
	public void excluir(Fabricante entidade) {

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
	public Fabricante salvar(Fabricante entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pesquisa(Fabricante pesquisa, Criteria consulta) {
		
		if (pesquisa != null){
			
			if (pesquisa.getCnpj() != null && !pesquisa.getCnpj().isEmpty())
				consulta.add(Restrictions.eq("cnpj", pesquisa.getCnpj()));
			
			if (pesquisa.getRazaoSocial() != null && !pesquisa.getRazaoSocial().isEmpty())
				consulta.add(Restrictions.ilike("razaosocial", pesquisa.getRazaoSocial(), MatchMode.ANYWHERE));
			
			if (pesquisa.getNomeFantasia() != null && !pesquisa.getNomeFantasia().isEmpty())
				consulta.add(Restrictions.ilike("nomefantasia", pesquisa.getNomeFantasia(), MatchMode.ANYWHERE));
			
			if (pesquisa.getId() != null)
				consulta.add(Restrictions.eq("id", pesquisa.getId()));
		}
		
	}

	@Override
	public Fabricante selecionar(Fabricante entidade) throws Exception {

		Fabricante profissao = null;
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Fabricante.class);
			
			this.pesquisa(entidade, consulta);
			
			profissao = (Fabricante) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return profissao;
	}
}
