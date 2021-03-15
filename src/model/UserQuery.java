package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// TODO: Auto-generated Javadoc
/**
 * The Class UserQuery.
 */
public class UserQuery {

	/** The em. */
	public static EntityManager em;

	/** The emf. */
	EntityManagerFactory emf;

	/**
	 * Instantiates a new user query.
	 */
	public UserQuery() {
		emf = Persistence.createEntityManagerFactory("AplicatieStiri");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	/**
	 * List user.
	 *
	 * @return the list
	 */
	public List<User> listUser() {
		return em.createNamedQuery("User.findAll", User.class).getResultList();
	}

}
