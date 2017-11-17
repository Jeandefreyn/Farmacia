package br.unisul.exemplo.dao;

import java.util.List;

import org.hibernate.Criteria;

public interface DAOI<T> {

	public void inserir(T entidade) throws Exception;
	public T salvar(T entidade) throws Exception;
	public List<T> listar(T entidade) throws Exception;
	public void excluir(T entidade) throws Exception;
	public void pesquisa(T pesquisa, Criteria consulta);
	public T selecionar(T entidade) throws Exception;
}
