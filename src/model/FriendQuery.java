package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
 
public class FriendQuery{
 
	 public static EntityManager em;
	    EntityManagerFactory emf;
	    
	    public FriendQuery()
	    {
	     emf=Persistence.createEntityManagerFactory("AplicatieStiri");
	     em=emf.createEntityManager();
	     em.getTransaction().begin();
	    }
		@PersistenceContext(unitName="AplicatieStiri")
	    public void setEntityManager(EntityManager entityManager) {}
		
		
	    public List<Friend> listFr()
	    {
	     return em.createNamedQuery("Friend.findAll",Friend.class).getResultList();
	    }   
 
} 
