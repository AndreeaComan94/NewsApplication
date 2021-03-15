package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ResourceQuery{

	 public static EntityManager em;
	    EntityManagerFactory emf;
	    
	    public ResourceQuery()
	    {
	     emf=Persistence.createEntityManagerFactory("AplicatieStiri");
	     em=emf.createEntityManager();
	     em.getTransaction().begin();
	    }
	    
	    public List<Resource> listRes()
	    {
	     return em.createNamedQuery("Resource.findAll",Resource.class).getResultList();
	    }   
 
}
