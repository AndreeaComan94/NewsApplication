 package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the friends database table.
 * 
 */
@Entity
@Table(name="friends")
@NamedQuery(name="Friend.findAll", query="SELECT f FROM Friend f")
public class Friend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id")
	private int id;
    
	private String nume;
	
	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="friend")
	private List<Comment> comments;

	//bi-directional many-to-one association to User
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne 
	private User user;
	
 
 
	
	public Friend() {
	}

	public Friend(User user,String nume)
	{
	 this.user=user;
     this.nume=nume;
    }
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNume() {
		return this.nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setFriend(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setFriend(null);

		return comment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

} 