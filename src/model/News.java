package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the news database table.
 * 
 */
@Entity
@Table(name="news")
@NamedQuery(name="News.findAll", query="SELECT n FROM News n")
 
public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String descriere;

	private String sursa;

	private String titlu;

 
	
	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="news")
	private List<Comment> comments;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(
		name="news_category"
		, joinColumns={
			@JoinColumn(name="news_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="category_id")
			}
		)
	private List<Category> categories;
	private List<User> users;
	public News() {
	}

	
	public News(String sursa, String descriere, Date date,  
			String titlu, User user) {
		super();
		this.sursa = sursa;
		this.descriere = descriere;
		this.date = date;
		this.titlu = titlu;
		this.user = user;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescriere() {
		return this.descriere;
	}

	 
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public String getSursa() {
		return this.sursa;
	}

	public int getUserId()
	{ 
	 return user.getId();
	}
	
	public List<User> getUsersId() {
		return this.users;
	}

	public void setSursa(String sursa) {
		this.sursa = sursa;
	}

	public String getTitlu() {
		return this.titlu;
	}

	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setNew(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setNew(null);

		return comment;
	}

	public User getUser() {
		return this.user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
 
}