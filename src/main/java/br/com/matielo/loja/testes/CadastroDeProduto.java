package br.com.matielo.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.matielo.loja.dao.CategoriaDao;
import br.com.matielo.loja.dao.ProdutoDao;
import br.com.matielo.loja.modelo.Categoria;
import br.com.matielo.loja.modelo.Produto;
import br.com.matielo.loja.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {

		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "SnapDragon", new BigDecimal("800.50"),celulares);

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
	    em.persist(celulares);
		
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

		em.getTransaction().commit();
		em.close();
	}
}
