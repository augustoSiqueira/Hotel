package br.com.hotel.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf;

	public static void gerarTabelas() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		emf = Persistence.createEntityManagerFactory("unit");
	}

	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	
}
