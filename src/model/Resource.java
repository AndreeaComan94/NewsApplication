package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the resource database table.
 * 
 */
@Entity
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id")
	private int id;

	private String description;

	private String url;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Resource() {
	}

	public Resource(String url,String description,User user) {
        this.url=url;
        this.description=description;
        this.user=user;
	}
   
	public Resource(int id,String url,String description,User user) {
		this.id=id;
        this.url=url;
        this.description=description;
        this.user=user;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
		System.out.println(id);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}