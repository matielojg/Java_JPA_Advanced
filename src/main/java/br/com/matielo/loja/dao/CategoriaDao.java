package br.com.matielo.loja.dao;

import javax.persistence.EntityManager;

import br.com.matielo.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}

	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}

	/**
	 * https://cursos.alura.com.br/course/persistencia-jpa-introducao-hibernate/task/88909
	 * minuto 5:00
	 * @param categoria
	 */
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria); //devolve o objeto em Managed
		this.em.remove(categoria); //remove do banco
	}
}
