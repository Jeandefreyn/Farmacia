package br.unisul.exemplo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.unisul.exemplo.domain.Usuario;
import br.unisul.exemplo.util.HibernateUtil;

public class UsuarioDAO implements DAOI<Usuario> {

	@Override
	public void inserir(Usuario entidade) {
		
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
	public List<Usuario> listar(Usuario entidade) {

		List<Usuario> lista = new ArrayList<Usuario>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			
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
	public void excluir(Usuario entidade) {

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
	public Usuario salvar(Usuario entidade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pesquisa(Usuario pesquisa, Criteria consulta) {
		
		if (pesquisa != null){
			
			if (pesquisa.getLogin() != null && !pesquisa.getLogin().isEmpty())
				consulta.add(Restrictions.ilike("login", pesquisa.getLogin(), MatchMode.ANYWHERE));
			
			if (pesquisa.getId() != null)
				consulta.add(Restrictions.eq("id", pesquisa.getId()));
		}
	}

	public Usuario buscarLoginSenha(String login, String senha) throws Exception {
		
		Usuario usuario = null;

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
		
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("login", login));
			consulta.add(Restrictions.eq("senha", senha));
			
			usuario = (Usuario) consulta.uniqueResult();
			
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return usuario;
	}

	@Override
	public Usuario selecionar(Usuario entidade) throws Exception {

		Usuario usuario = null;
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			
			this.pesquisa(entidade, consulta);
			
			usuario = (Usuario) consulta.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			sessao.close();
		}
		
		return usuario;
	}
}
