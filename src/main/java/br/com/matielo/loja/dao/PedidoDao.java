package br.com.matielo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.matielo.loja.modelo.Pedido;
import br.com.matielo.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM (p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	public List<RelatorioDeVendasVo> relatorioVendas() {
		String jpql = "SELECT new br.com.matielo.loja.vo.RelatorioDeVendasVo( " + "produto.nome, "
				+ "SUM(item.quantidade), " + "MAX(pedido.data)) " + "FROM Pedido pedido " + "JOIN pedido.itens item "
				+ "JOIN item.produto produto " + "GROUP BY produto.nome , item.quantidade "
				+ "ORDER BY item.quantidade DESC ";
		return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
	}

	/**
	 * metodo de buscar pedido com cliente prevendo o problema de lazyException
	 * pois se a conexão for fechada, o cliente esta com LAZY pattern, entao formatamos essa busca pra caso precise carregar cliente junto.
	 * https://cursos.alura.com.br/course/java-jpa-consultas-avancadas-performance-modelos-complexos/task/89338
	 * essa forma de Select é como se fosse EAGER
	 * @param id
	 * @return
	 */
	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
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
//	public Produto buscarPorId(Long id) {
//		return em.find(Produto.class, id);
//	}
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
