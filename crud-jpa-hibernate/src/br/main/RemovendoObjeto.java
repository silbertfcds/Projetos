package br.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.modelo.Cliente;

public class RemovendoObjeto {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemploPU");
		EntityManager em = emf.createEntityManager();
		
		Cliente cliente = em.find(Cliente.class, 3L);
		
		em.getTransaction().begin();
		em.remove(cliente);
		em.getTransaction().commit();
		
		System.out.println("Cliente REMOVIDO com sucesso!");
	}
}
