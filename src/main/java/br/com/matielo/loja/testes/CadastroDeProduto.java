package br.com.matielo.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.matielo.loja.dao.ProdutoDao;
import br.com.matielo.loja.modelo.Categoria;
import br.com.matielo.loja.modelo.Produto;
import br.com.matielo.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Produto celular = new Produto("Xiaomi Redmi", "SnapDragon", new BigDecimal("800.50"), Categoria.CELULARES);
		// parei no 5:33
		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao dao = new ProdutoDao(em);

		em.getTransaction().begin();
		dao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}
}
