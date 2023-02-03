package br.com.matielo.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.matielo.loja.modelo.Produto;

public class CadastroDeProduto {
	public static void main(String[] args) {
		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("SnapDragon");
		celular.setPreco(new BigDecimal("800.50"));

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();

		em.close();

	}
}
