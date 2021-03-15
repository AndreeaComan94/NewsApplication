package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NewsQuery {


	 public static EntityManager em;
	    EntityManagerFactory emf;
	    
	    public NewsQuery()
	    {
	     emf=Persistence.createEntityManagerFactory("AplicatieStiri");
	     em=emf.createEntityManager();
	     em.getTransaction().begin();
	    }
	    
	    public List<News> listNews()
	    {
	     return em.createNamedQuery("News.findAll",News.class).getResultList();
	    }   
	 
}
