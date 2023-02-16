package br.com.matielo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.matielo.loja.modelo.Categoria;
import br.com.matielo.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto); // devolve o objeto em Managed
		this.em.remove(produto); // remove do banco
	}

	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE  p.nome = :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	/**
	 * Consulta com jpql com JOIN de tabelas em 'p.categoria.nome'
	 * 
	 * @param nome
	 * @return
	 */
	public List<Produto> buscarPorNomeDaCategoria(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE  p.categoria.nome = :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	/**
	 * NamedQueries para exemplo somente
	 * 
	 * @param nome
	 * @return
	 */
	public List<Produto> buscarPorNomeDaCategoriaExemplo(String nome) {
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class).setParameter("nome", nome)
				.getResultList();
	}

	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE  p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
	}
}
