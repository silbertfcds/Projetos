package br.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.modelo.Cliente;

public class UpdateObjeto {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemploPU");
		EntityManager em = emf.createEntityManager();
		
		Cliente cliente = em.find(Cliente.class, 4L);
		
		cliente.setNome("Belinha");
		cliente.setIdade(2);
		cliente.setProfissao("Cadela");
		cliente.setSexo("F");
		
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		
		System.out.println("Cliente EDITADO com sucesso!");
	}

}
