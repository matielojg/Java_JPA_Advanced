package br.com.matielo.loja.testes;

import javax.persistence.EntityManager;

import br.com.matielo.loja.modelo.Categoria;
import br.com.matielo.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		Categoria celulares = new Categoria("CELULARES");
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		em.persist(celulares);

		celulares.setNome("XPTO");
		System.out.println(celulares.getNome().toString());

		em.flush();
		em.clear(); //deixa detached

		celulares = em.merge(celulares); // necessário utilizar o merge() para voltar ao estado managed e reatribuir ao
											// mesmo objeto, senao cria outra referência
		celulares.setNome("1234");
		System.out.println(celulares.getNome().toString());
		
		em.flush();
		em.remove(celulares);
		em.flush();
	}

}
