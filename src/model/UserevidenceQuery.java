package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserevidenceQuery {

	 public static EntityManager em;
	    EntityManagerFactory emf;
	    
	    public UserevidenceQuery()
	    {
	     emf=Persistence.createEntityManagerFactory("AplicatieStiri");
	     em=emf.createEntityManager();
	     em.getTransaction().begin();
	    }
	    
	    public List<UserevidenceQuery> listUser()
	    {
	     return em.createNamedQuery("UserevidenceQuery.findAll",UserevidenceQuery.class).getResultList();
	    }   
	  
}
