package br.com.matielo.loja.dao;

import javax.persistence.EntityManager;

import br.com.matielo.loja.modelo.Cliente;
import br.com.matielo.loja.modelo.Pedido;

public class ClienteDao {

	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}

//	public void atualizar(Pedido pedido) {
//		pedido = em.merge(pedido);
//		this.em.remove(pedido);
//	}
//
//	public void remover(Pedido pedido) {
//		pedido = em.merge(pedido); // devolve o objeto em Managed
//		this.em.remove(pedido); // remove do banco
//	}
//
//
//	public List<Pedido> buscarTodos() {
//		String jpql = "SELECT p FROM Pedido p";
//		return em.createQuery(jpql, Pedido.class).getResultList();
//	}
//
//	public List<Pedido> buscarPorNome(String nome) {
//		String jpql = "SELECT p FROM Pedido p WHERE  p.nome = :nome";
//		return em.createQuery(jpql, Pedido.class).setParameter("nome", nome).getResultList();
//	}
//
//	/**
//	 * Consulta com jpql com JOIN de tabelas em 'p.categoria.nome'
//	 * 
//	 * @param nome
//	 * @return
//	 */
//	public List<Pedido> buscarPorNomeDaCategoria(String nome) {
//		String jpql = "SELECT p FROM Pedido p WHERE  p.categoria.nome = :nome";
//		return em.createQuery(jpql, Pedido.class).setParameter("nome", nome).getResultList();
//	}
//
//	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
//		String jpql = "SELECT p.preco FROM Pedido p WHERE  p.nome = :nome";
//		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
//	}
}
