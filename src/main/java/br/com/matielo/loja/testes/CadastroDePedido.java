package br.com.matielo.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.matielo.loja.dao.CategoriaDao;
import br.com.matielo.loja.dao.ClienteDao;
import br.com.matielo.loja.dao.PedidoDao;
import br.com.matielo.loja.dao.ProdutoDao;
import br.com.matielo.loja.modelo.Categoria;
import br.com.matielo.loja.modelo.Cliente;
import br.com.matielo.loja.modelo.ItemPedido;
import br.com.matielo.loja.modelo.Pedido;
import br.com.matielo.loja.modelo.Produto;
import br.com.matielo.loja.util.JPAUtil;

public class CadastroDePedido {
	public static void main(String[] args) {

		popularBancoDeDados();

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		ClienteDao clienteDao = new ClienteDao(em);

		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);

		em.getTransaction().begin();

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));

		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();

	}

	public static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Cliente cliente = new Cliente("Rodrigo", "123456");

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);

		em.getTransaction().commit();
		em.close();
	}
}
