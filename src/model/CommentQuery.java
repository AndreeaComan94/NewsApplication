package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
 
public class CommentQuery{
 
	 public static EntityManager em;
	    EntityManagerFactory emf;
	    
	    public CommentQuery()
	    {
	     emf=Persistence.createEntityManagerFactory("AplicatieStiri");
	     em=emf.createEntityManager();
	     em.getTransaction().begin();
	    }
		@PersistenceContext(unitName="AplicatieStiri")
	    public void setEntityManager(EntityManager entityManager) {}
		
		
	    public List<Comment> listCom()
	    {
	     return em.createNamedQuery("Comment.findAll",Comment.class).getResultList();
	    }   
 
} 
